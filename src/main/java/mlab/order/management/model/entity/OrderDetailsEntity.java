package mlab.order.management.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class OrderDetailsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="order_id")
    private OrderEntity order;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name="product_id")
    private ProductEntity product;

    @Column(name = "product_quantity")
    private int productQuantity;

}
