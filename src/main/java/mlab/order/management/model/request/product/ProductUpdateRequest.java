package mlab.order.management.model.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

    private long id;
    @NotBlank(message = "Product name can not be empty")
    @JsonProperty("product_name")
    private String name;
    @NotBlank(message = "Password can not be empty")
    @JsonProperty("sku_no")
    private String skuId;
    @NotBlank(message = "Password can not be empty")
    @JsonProperty("category")
    private String category;
    @NotNull(message = "Password can not be empty")
    @Min(value = 1, message = "Minimul valid count is 1")
    @JsonProperty("quantity")
    private int count;

}
