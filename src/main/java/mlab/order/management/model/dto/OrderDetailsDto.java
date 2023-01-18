package mlab.order.management.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OrderDetailsDto {

    @JsonProperty("product")
    private ProductDto product;
    @JsonProperty("product_quantity")
    private int productQuantity;

}
