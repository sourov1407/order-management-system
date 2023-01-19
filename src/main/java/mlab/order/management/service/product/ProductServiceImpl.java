package mlab.order.management.service.product;

import lombok.RequiredArgsConstructor;
import mlab.order.management.exception.BadRequestException;
import mlab.order.management.exception.RecordNotFoundException;
import mlab.order.management.filter.Filter;
import mlab.order.management.model.dto.ProductDto;
import mlab.order.management.model.entity.ProductEntity;
import mlab.order.management.model.enums.QueryOperator;
import mlab.order.management.model.request.product.ProductCreateRequest;
import mlab.order.management.model.request.product.ProductUpdateRequest;
import mlab.order.management.repository.CustomProductRepository;
import mlab.order.management.repository.ProductRepository;
import mlab.order.management.service.BaseService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseService implements ProductService{

    private final CustomProductRepository customProductRepository;
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    @Override
    public ProductDto createProduct(ProductCreateRequest request) {
        productRepository.findDistinctByName(request.getName())
                .ifPresent(productEntity -> {
                    throw new BadRequestException(
                            getLocaleMessage("api.product.exist"));
                });

        ProductEntity product = mapper.mapToNewEntity(request);
        productRepository.save(product);
        return mapper.mapToDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductUpdateRequest productUpdateRequest) {
        ProductEntity product = productRepository.findById(productUpdateRequest.getId())
                .orElseThrow(() -> {
                    throw new RecordNotFoundException(
                            getLocaleMessage("api.product.notFound"));
                });
        mapper.mapUpdatedEntity(product, productUpdateRequest);
        productRepository.save(product);
        return mapper.mapToDto(product);
    }

    @Override
    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDto getProduct(long id) {
        ProductEntity product = productRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(
                        getLocaleMessage("api.product.notFound")));
        return mapper.mapToDto(product);
    }

    @Override
    public ProductDto deleteProduct(long id) {
        ProductEntity product = productRepository.findById(id)
                .map(productEntity -> {
                    productRepository.delete(productEntity);
                    return productEntity;
                })
                .orElseThrow(() -> new RecordNotFoundException(
                        getLocaleMessage("api.product.notFound")));

        return mapper.mapToDto(product);
    }

    @Override
    public List<ProductDto> searchProduct(String name, String sku,
                                          String category, Pageable pageable) {

        List<Filter> filters = new ArrayList<>();
        if(name != null) {
            filters.add(Filter.builder()
                    .field("name")
                    .operator(QueryOperator.EQUALS)
                    .value(name)
                    .build());
        }
        if (sku != null) {
            filters.add(Filter.builder()
                    .field("skuId")
                    .operator(QueryOperator.EQUALS)
                    .value(sku)
                    .build());
        }
        if (category != null) {
            filters.add(Filter.builder()
                    .field("category")
                    .operator(QueryOperator.EQUALS)
                    .value(category)
                    .build());
        }

        return customProductRepository.getQueryResultWithPagination(filters,
                pageable).stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

}
