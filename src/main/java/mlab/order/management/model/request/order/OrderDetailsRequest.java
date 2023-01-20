package mlab.order.management.model.request.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class OrderDetailsRequest {

    @NotNull(message = "User id can not be empty")
    @JsonProperty("product_id")
    private long id;
    @NotNull(message = "Quantity Can not be null")
    @JsonProperty("product_quantity")
    @Max(value = 4, message = "validation.property.order.quantity.Max.message")
    private int quantity;

}
