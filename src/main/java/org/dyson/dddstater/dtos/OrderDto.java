package org.dyson.dddstater.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class OrderDto {
    Long orderId;
    private String shippingName;
    private String billingName;
    private Long nextFreeItemId;
    private List<OrderItemDto> items = new ArrayList<>();
}
