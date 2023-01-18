package mlab.order.management.repository;

import mlab.order.management.model.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

    List<RoleEntity> findByNameIn(List<String> nameList);
    Optional<RoleEntity> findByName(String name);
}
