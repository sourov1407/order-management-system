package mlab.order.management.model.request.product;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {

    private long id;
    @NotBlank(message = "validation.property.product.name.NotBlank.message")
    @JsonProperty("product_name")
    private String name;
    @NotBlank(message = "validation.property.product.skuId.NotBlank.message")
    @JsonProperty("sku_no")
    private String skuId;
    @NotBlank(message = "validation.property.product.category.NotBlank.message")
    @JsonProperty("category")
    private String category;
    @NotNull(message = "validation.property.product.count.NotNull.message")
    @Min(value = 1, message = "validation.property.product.count.Min.message")
    @JsonProperty("quantity")
    private int count;
    @JsonProperty("issue_date")
    @Pattern(regexp = "^(0[1-9]|1[0-2])-(0[1-9]|1\\d|2\\d|3[01])-(19|20)\\d{2}$",
            message = "validation.property.product.issueDate.Pattern.message")
    private String issueDate;

}
