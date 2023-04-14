package vn.unicloud.fnb.dto

import org.springframework.data.domain.Pageable
import java.math.BigDecimal
import java.util.Optional

data class OrderQuery(
    val orderId: Optional<Long>,
    val pageable: Pageable
)

data class CreateOrderCommand(
    val shippingName: String? = null,
    val billingName: String? = null,
    val nextFreeItemId: Long? = null,
    val items: List<OrderItemDto> = ArrayList(),
){
    companion object{
        fun newInstance(): CreateOrderCommand?{
            return null;
        }
    }
}

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

data class OrderDto(
    val orderId: Long
)

data class OrderItemDto(
    val description: String? = null,
    val quantity: Int = 0,
    val price: BigDecimal? = null,
    val subTotal: BigDecimal? = null,
)
