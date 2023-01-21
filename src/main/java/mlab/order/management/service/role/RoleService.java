package mlab.order.management.service.role;

import lombok.RequiredArgsConstructor;
import mlab.order.management.model.entity.RoleEntity;
import mlab.order.management.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public List<RoleEntity> findAll(){ return roleRepository.findAll(); }

    public List<RoleEntity> createRoles(List<String> roles) {

        List<RoleEntity> roleEntities = roles.stream()
                .map(s -> RoleEntity.builder().name(s).build())
                .collect(Collectors.toList());
        return roleRepository.saveAll(roleEntities);
    }
}
