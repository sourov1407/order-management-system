package mlab.order.management.service.order;

import lombok.RequiredArgsConstructor;
import mlab.order.management.exception.BadRequestException;
import mlab.order.management.exception.RecordNotFoundException;
import mlab.order.management.model.dto.EmailDto;
import mlab.order.management.model.dto.OrderDto;
import mlab.order.management.model.entity.OrderDetailsEntity;
import mlab.order.management.model.entity.OrderEntity;
import mlab.order.management.model.entity.UserEntity;
import mlab.order.management.model.request.order.OrderCreateRequest;
import mlab.order.management.repository.OrderRepository;
import mlab.order.management.repository.ProductRepository;
import mlab.order.management.repository.UserRepository;
import mlab.order.management.service.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl extends BaseService implements OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final OrderMapper mapper;

    @Override
    public OrderDto createOrder(OrderCreateRequest request) {
        validateOrderRequest(request);
        UserEntity userEntity = userRepository
                .findById(request.getId())
                .orElseThrow(() -> new RecordNotFoundException(getLocaleMessage("api.user.notFound")));

        Set<OrderDetailsEntity> orderEntities = getOrderDetailsEntities(request);
        OrderEntity orderEntity = OrderEntity
                .builder()
                .user(userEntity)
                .orderDetailsList(orderEntities)
                .build();

        orderEntities.forEach(orderDetailsEntity ->
                orderDetailsEntity.setOrder(orderEntity));
        userEntity.getOrders().add(orderEntity);
        userRepository.save(userEntity);
        sendEmail(EmailDto.builder()
                .email("test@abc.com")
                .body("Test mail sending")
                .build());

        return mapper.mapToDto(orderEntity);
    }

    @Override
    public List<OrderDto> searchOrder(String name, String category, Pageable pageable) {

        if (name != null) {
            return getOrdersByProductName(name, pageable);
        } else if (category != null) {
            return getOrdersByProductCategory(category, pageable);
        } else return orderRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> searchOrdersByUser(long id) {
        return orderRepository.getAllByUser_Id(id).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    private List<OrderDto> getOrdersByProductCategory(String category, Pageable pageable) {
        return orderRepository.getAllByOrderDetailsList_Product_Category(category,
                pageable).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    private List<OrderDto> getOrdersByProductName(String name, Pageable pageable) {
        return orderRepository.getAllByOrderDetailsList_Product_Name(name,
                pageable).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    private Set<OrderDetailsEntity> getOrderDetailsEntities(OrderCreateRequest request) {
        return request.getOrderDetails().stream()
                .map(orderDetailsRequest ->
                        OrderDetailsEntity.builder()
                                .product(productRepository
                                        .findById(orderDetailsRequest.getId())
                                        .map(productEntity -> {
                                            if (productEntity.getCount() < orderDetailsRequest.getQuantity()) {
                                                throw new BadRequestException("Requested product count exceeds the quantity");
                                            }
                                            productEntity.setCount(productEntity.getCount() - orderDetailsRequest.getQuantity());
                                            return productEntity;
                                        })
                                        .orElseThrow(() -> new RecordNotFoundException("Product not found")))
                                .productQuantity(orderDetailsRequest.getQuantity())
                                .build()
                ).collect(Collectors.toSet());
    }

    private void validateOrderRequest(OrderCreateRequest request) {
        if (CollectionUtils.isEmpty(request.getOrderDetails())) {
            throw new BadRequestException("Order details can not be empty");
        }
    }
}
