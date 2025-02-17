package com.devsurfer.purepicks.model.entity.order;

import com.devsurfer.purepicks.model.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Tag(name = "订单信息实体", description = "订单信息实体")
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo extends BaseEntity {

    @Schema(description = "会员id")
    private Long userId;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "订单号")
    private String orderNo;

    @Schema(description = "使用的优惠券id")
    private String couponId;

    @Schema(description = "订单总金额")
    private BigDecimal totalAmount;

    @Schema(description = "原价金额")
    private BigDecimal originalTotalAmount;

    @Schema(description = "运费")
    private BigDecimal freightFee;

    @Schema(description = "支付方式")
    private String payType;

    @Schema(description = "订单状态")
    private String orderStatus;

    @Schema(description = "收货人姓名")
    private String receiverName;

    @Schema(description = "收货人手机号")
    private String receiverPhone;

    @Schema(description = "收货人邮编")
    private String receiverPostCode;

    @Schema(description = "收货人省份/直辖市")
    private String receiverProvince;

    @Schema(description = "收货人城市")
    private String receiverCity;

    @Schema(description = "收货人区/县")
    private String receiverDistrict;

    @Schema(description = "收货人详细地址")
    private String receiverAddress;

    @Schema(description = "支付时间")
    private Date paymentTime;

    @Schema(description = "发货时间")
    private Date deliveryTime;

    @Schema(description = "收货时间")
    private Date receiveTime;

    @Schema(description = "订单备注")
    private String remark;

    @Schema(description = "订单取消时间")
    private Date cancelTime;

    @Schema(description = "订单取消原因")
    private Date cancelReason;

}
