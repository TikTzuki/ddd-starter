package org.dyson.dddstarter

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import java.math.BigDecimal

data class CreateOrderCommand(
    var orderId: Long? = null,
    val shippingName: String? = null,
    val billingName: String? = null,
    val nextFreeItemId: Long? = null,
    val items: List<OrderItemDto> = ArrayList(),
)

data class OrderItemDto(
    val description: String? = null,
    val quantity: Int = 0,
    val price: BigDecimal? = null,
    val subTotal: BigDecimal? = null,
)
