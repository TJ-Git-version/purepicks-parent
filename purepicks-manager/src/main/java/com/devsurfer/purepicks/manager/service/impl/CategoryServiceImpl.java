package com.devsurfer.purepicks.manager.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.excel.EasyExcel;
import com.devsurfer.purepicks.manager.helper.CategoryHelper;
import com.devsurfer.purepicks.manager.listener.ReadCategoryListener;
import com.devsurfer.purepicks.manager.mapper.CategoryMapper;
import com.devsurfer.purepicks.manager.service.CategoryService;
import com.devsurfer.purepicks.model.dto.category.CategoryDeleteDto;
import com.devsurfer.purepicks.model.dto.category.CategoryInsertDto;
import com.devsurfer.purepicks.model.dto.category.CategoryQueryDto;
import com.devsurfer.purepicks.model.dto.category.CategoryUpdateDto;
import com.devsurfer.purepicks.model.entity.category.Category;
import com.devsurfer.purepicks.model.enums.redis.RedisKeyConstantEnum;
import com.devsurfer.purepicks.model.event.DeleteCategoryEvent;
import com.devsurfer.purepicks.model.event.UpdateCategoryEvent;
import com.devsurfer.purepicks.model.result.ResultCodeEnum;
import com.devsurfer.purepicks.model.vo.category.CategoryExcelVo;
import com.devsurfer.purepicks.model.vo.category.CategoryVo;
import com.devsurfer.purepicks.service.handle.PurePicksException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:46
 */
@Service
@Slf4j
@AllArgsConstructor
@SuppressWarnings("all")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper categoryMapper;

    private final RedisTemplate<String, Object> redisTemplate;

    private final ApplicationEventPublisher publisher;

    @Override
    public List<CategoryVo> findCategoryList(CategoryQueryDto categoryQueryDto) {
        List<CategoryVo> cacheCategoryList = (List<CategoryVo>) redisTemplate.opsForValue().get(RedisKeyConstantEnum.MANAGER_CATEGORY_TREE.getKey());
        if (cacheCategoryList != null && !cacheCategoryList.isEmpty()) {
            return cacheCategoryList;
        }
        List<Category> categoryList = categoryMapper.findCategoryList(categoryQueryDto);
        if (CollectionUtil.isEmpty(categoryList)) return Collections.emptyList();
        List<CategoryVo> categoryVos = CategoryHelper.buildTree(BeanUtil.copyToList(categoryList, CategoryVo.class));
        publisher.publishEvent(new UpdateCategoryEvent(categoryVos));
        return categoryVos;
    }


    @Override
    public void addCategory(CategoryInsertDto categoryInsertDto) {
        Category category = categoryMapper.selectByNameAndParentId(categoryInsertDto.getName(), categoryInsertDto.getParentId());
        if (category != null) {
            PurePicksException.error(ResultCodeEnum.CATEGORY_NAME_EXISTS_ERROR);
        }
        categoryMapper.insertCategory(BeanUtil.copyProperties(categoryInsertDto, Category.class));
        publisher.publishEvent(new DeleteCategoryEvent());
    }

    @Override
    public void editCategory(CategoryUpdateDto categoryUpdateDto) {
        Category category = categoryMapper.selectByNotIdNameAndParentId(categoryUpdateDto.getId(), categoryUpdateDto.getName(), categoryUpdateDto.getParentId());
        if (category != null) {
            PurePicksException.error(ResultCodeEnum.CATEGORY_NAME_EXISTS_ERROR);
        }
        categoryMapper.updateCategory(BeanUtil.copyProperties(categoryUpdateDto, Category.class));
        publisher.publishEvent(new DeleteCategoryEvent());
    }

    @Override
    public void removeCategory(CategoryDeleteDto categoryDeleteDto) {
        Long sonCount = categoryMapper.countByParentId(categoryDeleteDto.getCategoryId());
        if (sonCount > 0) {
            PurePicksException.error(ResultCodeEnum.CATEGORY_DELETE_ERROR);
        }
        categoryMapper.deleteById(categoryDeleteDto.getCategoryId());
        publisher.publishEvent(new DeleteCategoryEvent());
    }

    @Override
    public void importExcelTemplate(HttpServletResponse response) {
        try {
            setExcelResponse("导入excel分类模版", response);
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类").doWrite(Collections.emptyList());
        } catch (IOException e) {
            log.error("excel 导出模版失败: " + e);
            PurePicksException.error(ResultCodeEnum.EXCEL_FILE_EXPORT_ERROR);
        }
    }

    @Override
    public void exportExcel(CategoryQueryDto categoryQueryDto, HttpServletResponse response) {
        try {
            List<Category> categoryList = categoryMapper.findCategoryList(categoryQueryDto);
            List<CategoryExcelVo> categoryExcelVos = CategoryHelper.buildExcelCategory(BeanUtil.copyToList(categoryList, CategoryVo.class));
            log.info("导出Excel分类: {}", JSONUtil.toJsonStr(categoryExcelVos));
            setExcelResponse("导出分类" + LocalDateTimeUtil.format(LocalDate.now(), DateTimeFormatter.ofPattern("yyyy-MM-dd")), response);
            EasyExcel.write(response.getOutputStream(), CategoryExcelVo.class).sheet("分类").doWrite(categoryExcelVos);
        } catch (IOException e) {
            log.error("导出Excel分类失败,异常原因:{}", JSONUtil.toJsonStr(e));
            PurePicksException.error(ResultCodeEnum.EXCEL_FILE_EXPORT_ERROR);
        }

    }

    @Override
    public void importExcel(MultipartFile excelFile) {
        try {
            EasyExcel.read(excelFile.getInputStream(), CategoryExcelVo.class, new ReadCategoryListener(categoryMapper, categoryMapper.findCategoryList(null))).doReadAll();
            publisher.publishEvent(new DeleteCategoryEvent());
        } catch (IOException e) {
            PurePicksException.error(ResultCodeEnum.EXCEL_FILE_EXPORT_ERROR);
        }
    }

    private static void setExcelResponse(String excelName, HttpServletResponse response) {
        // 设置响应结果类型
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode(excelName, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
    }
}
