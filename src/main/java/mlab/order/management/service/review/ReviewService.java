package mlab.order.management.service.review;

import mlab.order.management.model.dto.ReviewDto;
import mlab.order.management.model.request.review.ReviewCreateRequest;
import org.springframework.transaction.annotation.Transactional;

public interface ReviewService {

    @Transactional
    ReviewDto createReview(ReviewCreateRequest request);
}
