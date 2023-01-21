package mlab.order.management.model.request.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {

    @NotNull(message = "validation.property.review.userId.NotNull.message")
    @JsonProperty("user_id")
    private long userId;
    @NotNull(message = "validation.property.review.productId.NotNull.message")
    @JsonProperty("product_id")
    private long productId;
    @NotNull(message = "validation.property.review.rating.NotNull.message")
    @JsonProperty("rating")
    private double rating;
    @NotBlank(message = "validation.property.review.review.NotBlank.message")
    @JsonProperty("review")
    private String review;


}
