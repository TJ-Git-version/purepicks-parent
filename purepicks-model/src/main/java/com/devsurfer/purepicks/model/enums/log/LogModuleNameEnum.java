package com.devsurfer.purepicks.model.enums.log;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/3/9 14:10
 * description 日志-模块名称
 */
@Getter
@AllArgsConstructor
@Schema(description = "日志模块名称枚举")
public enum LogModuleNameEnum {

    @Schema(description = "品牌添加")
    BRAND_ADD("brand_add", "品牌添加"),

    @Schema(description = "品牌编辑")
    BRAND_EDIT("brand_edit", "品牌修改"),

    @Schema(description = "品牌删除")
    BRAND_DELETE("brand_delete", "品牌删除"),

    @Schema(description = "分类添加")
    CATEGORY_ADD("category_add", "分类添加"),

    @Schema(description = "分类编辑")
    CATEGORY_EDIT("category_edit", "分类编辑"),

    @Schema(description = "分类删除")
    CATEGORY_DELETE("category_delete", "分类删除"),

    @Schema(description = "文件上传")
    FILE_UPLOAD("file_upload", "文件上传"),

    // TODO 待完善日志枚举
    @Schema(description = "商品添加")
    PRODUCT_ADD("product_add", "商品添加"),

    @Schema(description = "商品规格管理")
    PRODUCT_SPEC_MODULE("product_spec", "商品规格管理"),

    @Schema(description = "商品单元管理")
    PRODUCT_UNIT_MODULE("product_unit", "商品单元管理"),

    @Schema(description = "菜单管理")
    MENU_MODULE("menu", "菜单管理"),

    @Schema(description = "角色管理")
    ROLE_MODULE("role", "角色管理"),

    @Schema(description = "用户添加")
    USER_ADD("user_add", "用户添加"),
    ;

    private final String value;

    private final String name;

}
