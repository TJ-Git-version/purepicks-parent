package com.devsurfer.purepicks.model.dto.system.user;

import com.devsurfer.purepicks.model.dto.base.BaseQueryPageDto;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Dev Surfer
 * @version 1.0.0
 * date 2025/1/12 20:25
 * description 系统用户查询实体
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Tag(name = "系统用户查询实体")
public class SysUserQueryDto extends BaseQueryPageDto {

    @Schema(description = "关键字: 用户名, 姓名, 手机号码")
    private String keyword;

    @Schema(description = "创建时间: 开始时间")
    private String createTimeBegin;

    @Schema(description = "创建时间: 结束时间")
    private String createTimeEnd;

}
