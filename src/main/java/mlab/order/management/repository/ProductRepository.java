package mlab.order.management.repository;

import mlab.order.management.model.entity.ProductEntity;
import mlab.order.management.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<ProductEntity,Long>, JpaSpecificationExecutor<ProductEntity> {

    List<ProductEntity> findByIdIn(Set<Long> ids);

    Optional<ProductEntity> findDistinctByName(String name);

}
