package mlab.order.management.service.product;

import lombok.RequiredArgsConstructor;
import mlab.order.management.model.dto.ProductDto;
import mlab.order.management.model.dto.UserDto;
import mlab.order.management.model.entity.ProductEntity;
import mlab.order.management.model.entity.RoleEntity;
import mlab.order.management.model.entity.UserEntity;
import mlab.order.management.model.request.product.ProductCreateRequest;
import mlab.order.management.model.request.product.ProductUpdateRequest;
import mlab.order.management.model.request.user.UserCreateRequest;
import mlab.order.management.model.request.user.UserUpdateRequest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProductMapper {

    public ProductDto mapToDto(ProductEntity productEntity) {
        return ProductDto.builder()
                .name(productEntity.getName())
                .category(productEntity.getCategory())
                .count(productEntity.getCount())
                .skuId(productEntity.getSkuId())
                .build();
    }

    public ProductEntity mapToNewEntity(ProductCreateRequest request) {
        return ProductEntity.builder()
                .name(request.getName())
                .category(request.getCategory())
                .count(request.getCount())
                .skuId(request.getSkuId())
                .build();
    }

    public void mapUpdatedEntity(ProductEntity entity, ProductUpdateRequest request) {
        entity.setCategory(request.getCategory());
        entity.setCount(request.getCount());
        entity.setSkuId(request.getSkuId());
        entity.setName(request.getName());
    }
}
