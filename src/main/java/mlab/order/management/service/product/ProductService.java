package mlab.order.management.service.product;

import mlab.order.management.filter.Filter;
import mlab.order.management.model.dto.ProductDto;
import mlab.order.management.model.entity.ProductEntity;
import mlab.order.management.model.request.product.ProductCreateRequest;
import mlab.order.management.model.request.product.ProductUpdateRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductService {
    @Transactional
    ProductDto createProduct(ProductCreateRequest request);

    @Transactional
    ProductDto updateProduct(ProductUpdateRequest userUpdateRequest);

    @Transactional
    List<ProductDto> getAllProduct();

    @Transactional
    ProductDto getProduct(long id);

    @Transactional
    ProductDto deleteProduct(long id);

    @Transactional
    List<ProductDto> searchProduct(String name, String sku,
                                   String category, Pageable pageable);
}
