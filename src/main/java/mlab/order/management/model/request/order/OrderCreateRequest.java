package mlab.order.management.model.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Builder
public class OrderCreateRequest {

    @NotNull(message = "validation.property.order.id.NotBlank.message")
    @JsonProperty("user_id")
    private long id;
    @Valid
    @JsonProperty("order_details")
    private List<OrderDetailsRequest> orderDetails;

}
