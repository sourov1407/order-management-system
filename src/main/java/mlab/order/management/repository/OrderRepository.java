package mlab.order.management.repository;

import mlab.order.management.model.entity.OrderEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Set;

public interface OrderRepository extends JpaRepository<OrderEntity,Long>, JpaSpecificationExecutor<OrderEntity> {

    List<OrderEntity> findByIdIn(Set<Long> ids);

    List<OrderEntity> getAllByUser_Id(long id);

    List<OrderEntity> getAllByOrderDetailsList_Product_Name(String name, Pageable pageable);

    List<OrderEntity> getAllByOrderDetailsList_Product_Category(String name, Pageable pageable);

}
