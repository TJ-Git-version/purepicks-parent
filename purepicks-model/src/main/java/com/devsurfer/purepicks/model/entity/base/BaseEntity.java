package com.devsurfer.purepicks.model.entity.base;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/11 14:32
 * description 抽取公共实体类信息
 */
@Data
public class BaseEntity implements Serializable {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 逻辑删除标识
     */
    private Integer isDeleted;

}
