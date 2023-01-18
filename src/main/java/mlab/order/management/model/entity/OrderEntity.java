package mlab.order.management.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderEntity extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY, mappedBy = "order")
    private Set<OrderDetailsEntity> orderDetailsList = new HashSet<>();

    public void addOrderDetails(OrderDetailsEntity orderDetailsEntity) {
        orderDetailsList.add(orderDetailsEntity);
        orderDetailsEntity.setOrder(this);
    }

}
