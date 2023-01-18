package mlab.order.management.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductDto {

    @JsonProperty("product_name")
    private String name;
    @JsonProperty("sku_no")
    private String skuId;
    private String category;
    @JsonProperty("quantity")
    private int count;

}
