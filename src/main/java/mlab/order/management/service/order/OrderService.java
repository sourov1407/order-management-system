package mlab.order.management.service.order;

import mlab.order.management.model.dto.OrderDto;
import mlab.order.management.model.request.order.OrderCreateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {

    @Transactional
    OrderDto createOrder(OrderCreateRequest request);

    @Transactional
    List<OrderDto> searchOrder(String name, String category, Pageable pageable);

    @Transactional
    List<OrderDto> searchOrdersByUser(long id);
}
