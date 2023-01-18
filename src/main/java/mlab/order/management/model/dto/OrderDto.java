package mlab.order.management.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import mlab.order.management.model.entity.OrderDetailsEntity;
import mlab.order.management.model.entity.UserEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public class OrderDto {

    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("order_details")
    private Set<OrderDetailsDto> orderDetailsDtos;

}
