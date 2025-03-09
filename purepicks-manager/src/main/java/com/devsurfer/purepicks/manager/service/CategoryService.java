package com.devsurfer.purepicks.manager.service;

import com.devsurfer.purepicks.model.dto.category.CategoryDeleteDto;
import com.devsurfer.purepicks.model.dto.category.CategoryInsertDto;
import com.devsurfer.purepicks.model.dto.category.CategoryQueryDto;
import com.devsurfer.purepicks.model.dto.category.CategoryUpdateDto;
import com.devsurfer.purepicks.model.vo.category.CategoryVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/19 23:45
 * description 分类管理接口实现
 */
public interface CategoryService {

    List<CategoryVo> findCategoryList(CategoryQueryDto categoryQueryDto);

    void addCategory(CategoryInsertDto categoryInsertDto);

    void editCategory(CategoryUpdateDto categoryUpdateDto);

    void removeCategory(CategoryDeleteDto categoryDeleteDto);

    void importExcelTemplate(HttpServletResponse response);

    void exportExcel(CategoryQueryDto categoryQueryDto, HttpServletResponse response);

    void importExcel(MultipartFile excelFile);

}
