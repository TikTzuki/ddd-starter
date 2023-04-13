package org.dyson.dddstarter

import java.math.BigDecimal

data class CreateOrderCommand(
    val shippingName: String? = null,
    val billingName: String? = null,
    val nextFreeItemId: Long? = null,
    val items: List<OrderItemDto> = ArrayList(),
)

data class UpdateOrderCommand(
    var orderId: Long? = null,
    val shippingName: String? = null,
    val billingName: String? = null,
    val nextFreeItemId: Long? = null,
    val items: List<OrderItemDto> = ArrayList(),
)

data class DeleteOrderCommand(
    var id: Long? = null
)

data class OrderItemDto(
    val description: String? = null,
    val quantity: Int = 0,
    val price: BigDecimal? = null,
    val subTotal: BigDecimal? = null,
)
