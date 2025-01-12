package com.devsurfer.purepicks.model.entity.system;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.util.Date;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 17:28
 * description 系统角色实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysRole extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    // 主键ID
    private Long id;

    // 角色名称
    private String roleName;

    // 名称编号
    private String roleCode;

    // 角色简介
    private String desc;

    // 创建时间
    private Date createTime;

    // 更新时间
    private Date updateTime;

    // 逻辑删除
    private Integer isDeleted;

}
