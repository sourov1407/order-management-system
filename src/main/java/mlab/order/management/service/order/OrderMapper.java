package mlab.order.management.service.order;

import lombok.RequiredArgsConstructor;
import mlab.order.management.model.dto.OrderDetailsDto;
import mlab.order.management.model.dto.OrderDto;
import mlab.order.management.model.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderMapper {
    private final OrderDetailsMapper orderDetailsMapper;

    public OrderDto mapToDto(OrderEntity order) {
        order.getOrderDetailsList();
        return OrderDto.builder()
                .userId(order.getUser().getId())
                .orderDetailsDtos(convertOrderDetailsToDto(order))
                .build();
    }

    private Set<OrderDetailsDto> convertOrderDetailsToDto(OrderEntity order) {
        return order.getOrderDetailsList().stream()
                .map(orderDetailsMapper::mapToDto)
                .collect(Collectors.toSet());
    }
}
