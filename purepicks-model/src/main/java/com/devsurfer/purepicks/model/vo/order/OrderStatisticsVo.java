package com.devsurfer.purepicks.model.vo.order;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Tag(name = "订单统计响应对象", description = "订单统计响应对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatisticsVo {

    @Schema(description = "echarts图表-横坐标-日期列表")
    private List<String> dateList;

    @Schema(description = "echarts图表-纵坐标-订单金额列表")
    private List<BigDecimal> totalAmountList;

    @Schema(description = "echarts图表-纵坐标-订单总金额列表")
    private BigDecimal totalAmount;

}
