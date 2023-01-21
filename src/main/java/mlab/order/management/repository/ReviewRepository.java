package mlab.order.management.repository;

import mlab.order.management.model.entity.ProductEntity;
import mlab.order.management.model.entity.ReviewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ReviewRepository extends JpaRepository<ReviewEntity,Long> {

}
