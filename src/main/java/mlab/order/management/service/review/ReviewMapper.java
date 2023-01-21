package mlab.order.management.service.review;

import lombok.RequiredArgsConstructor;
import mlab.order.management.model.dto.ReviewDto;
import mlab.order.management.model.entity.ReviewEntity;
import mlab.order.management.model.request.review.ReviewCreateRequest;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewMapper {

    public ReviewDto mapToDto(ReviewEntity reviewEntity) {
        return ReviewDto.builder()
                .rating(reviewEntity.getRating())
                .review(reviewEntity.getReview())
                .productId(reviewEntity.getProduct().getId())
                .build();

    }

    public ReviewEntity mapToNewEntity(ReviewCreateRequest request) {
        return ReviewEntity.builder()
                .rating(request.getRating())
                .review(request.getReview())
                .build();
    }
}
