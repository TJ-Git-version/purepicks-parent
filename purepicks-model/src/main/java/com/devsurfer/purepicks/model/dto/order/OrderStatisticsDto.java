package com.devsurfer.purepicks.model.dto.order;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Tag(name = "订单统计查询实体", description = "订单统计查询实体")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatisticsDto {

    @Schema(description = "订单统计开始日期")
    private String createTimeBegin;

    @Schema(description = "订单统计结束日期")
    private String createTimeEnd;

}
