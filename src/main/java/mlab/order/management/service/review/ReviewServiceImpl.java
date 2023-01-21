package mlab.order.management.service.review;

import lombok.RequiredArgsConstructor;
import mlab.order.management.exception.BadRequestException;
import mlab.order.management.model.dto.ReviewDto;
import mlab.order.management.model.entity.ProductEntity;
import mlab.order.management.model.entity.ReviewEntity;
import mlab.order.management.model.entity.UserEntity;
import mlab.order.management.model.request.review.ReviewCreateRequest;
import mlab.order.management.repository.ProductRepository;
import mlab.order.management.repository.UserRepository;
import mlab.order.management.service.BaseService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl extends BaseService implements ReviewService{

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ReviewMapper mapper;

    @Override
    public ReviewDto createReview(ReviewCreateRequest request) {
        UserEntity user = getUser(request);
        ProductEntity product = getProduct(request);
        ReviewEntity review = mapper.mapToNewEntity(request);

        user.getReviews().add(review);
        review.setUser(user);
        review.setProduct(product);
        userRepository.save(user);

        return mapper.mapToDto(review);
    }

    private ProductEntity getProduct(ReviewCreateRequest request) {
        return productRepository.findById(request.getProductId())
                .orElseThrow(() -> new BadRequestException(getLocaleMessage("api.product.notFound")));
    }

    private UserEntity getUser(ReviewCreateRequest request) {
        return userRepository.findById(request.getUserId())
                .orElseThrow(() -> new BadRequestException(getLocaleMessage("api.user.notFound")));
    }
}
