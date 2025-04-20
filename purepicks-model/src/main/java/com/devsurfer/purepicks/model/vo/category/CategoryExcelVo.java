package com.devsurfer.purepicks.model.vo.category;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentFontStyle;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.enums.BooleanEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.devsurfer.purepicks.model.enums.annocation.category.CategoryStatus;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/2/4 20:41
 * description TODO
 */
@Data
@Tag(name = "导出Excel菜单实体", description = "导出Excel菜单实体")
@NoArgsConstructor
@AllArgsConstructor
@ContentRowHeight(value = 18)
public class CategoryExcelVo {

    @ExcelIgnore
    private Long id;

    @ColumnWidth(value = 35)
    @ExcelProperty(value = "分类名称", index = 0)
    @ContentFontStyle(fontHeightInPoints = 16, color = 10)
    private String categoryName;

    @ColumnWidth(value = 12)
    @ExcelProperty(value = "状态", index = 2)
    @ContentFontStyle(fontHeightInPoints = 16)
    @ContentStyle(
            locked = BooleanEnum.TRUE,
            horizontalAlignment = HorizontalAlignmentEnum.CENTER,
            fillBackgroundColor = 22 // 设置浅灰色背景
    )
    private String status;

    public Integer getConvertStatus() {
        return CategoryStatus.getByMessage(this.status).getCode();
    }

}
