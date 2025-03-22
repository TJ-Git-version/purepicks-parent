package com.devsurfer.purepicks.model.vo.h5;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author Dev Surfer
 */
@Data
@Schema(description = "分类响应实体")
public class CategoryVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "父级ID")
    private Long parentId;

    @Schema(description = "分类名称")
    private String name;

    @Schema(description = "图标")
    private String imageUrl;

    @Schema(description = "排序")
    private Integer orderNum;

    @Schema(description = "状态(0 不可见 | 1 可见)")
    private Integer status;

    @Schema(description = "创建时间")
    private Date createTime;

    @Schema(description = "下级分类")
    private List<CategoryVo> children;

}
