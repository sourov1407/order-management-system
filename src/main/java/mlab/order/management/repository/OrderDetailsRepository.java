package mlab.order.management.repository;

import mlab.order.management.model.entity.OrderDetailsEntity;
import mlab.order.management.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface OrderDetailsRepository extends JpaRepository<OrderDetailsEntity,Long> {

    List<OrderEntity> findByIdIn(Set<Long> ids);

}
