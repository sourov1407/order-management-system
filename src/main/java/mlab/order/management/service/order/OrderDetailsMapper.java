package mlab.order.management.service.order;

import lombok.RequiredArgsConstructor;
import mlab.order.management.model.dto.OrderDetailsDto;
import mlab.order.management.model.entity.OrderDetailsEntity;
import mlab.order.management.service.product.ProductMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderDetailsMapper {

    private final ProductMapper productMapper;

    public OrderDetailsDto mapToDto(OrderDetailsEntity orderDetailsEntity) {
        return OrderDetailsDto.builder()
                .product(productMapper.mapToDto(orderDetailsEntity.getProduct()))
                .productQuantity(orderDetailsEntity.getProductQuantity())
                .build();

    }
}
