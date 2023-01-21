package mlab.order.management.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReviewDto {

    @JsonProperty("product_id")
    private long productId;
    @JsonProperty("rating")
    private double rating;
    @JsonProperty("review")
    private String review;

}
