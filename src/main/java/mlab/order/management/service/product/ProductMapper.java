package mlab.order.management.service.product;

import lombok.RequiredArgsConstructor;
import mlab.order.management.model.dto.ProductDto;
import mlab.order.management.model.entity.ProductEntity;
import mlab.order.management.model.request.product.ProductCreateRequest;
import mlab.order.management.model.request.product.ProductUpdateRequest;
import org.springframework.stereotype.Component;

import static mlab.order.management.util.DateTimeConvertUtil.convertApiToDbDate;
import static mlab.order.management.util.DateTimeConvertUtil.convertDbToApiDate;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductDto mapToDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .name(productEntity.getName())
                .category(productEntity.getCategory())
                .count(productEntity.getCount())
                .skuId(productEntity.getSkuId())
                .issueDate(convertDbToApiDate(productEntity.getIssueDate()))
                .build();
    }

    public ProductEntity mapToNewEntity(ProductCreateRequest request) {
        return ProductEntity.builder()
                .name(request.getName())
                .category(request.getCategory())
                .count(request.getCount())
                .skuId(request.getSkuId())
                .issueDate(convertApiToDbDate(request.getIssueDate()))
                .build();
    }

    public void mapUpdatedEntity(ProductEntity entity, ProductUpdateRequest request) {
        entity.setCategory(request.getCategory());
        entity.setCount(request.getCount());
        entity.setSkuId(request.getSkuId());
        entity.setName(request.getName());
        entity.setIssueDate(convertApiToDbDate(request.getIssueDate()));
    }
}
