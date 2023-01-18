package mlab.order.management.service.user;

import lombok.RequiredArgsConstructor;
import mlab.order.management.model.dto.UserDto;
import mlab.order.management.model.entity.RoleEntity;
import mlab.order.management.model.entity.UserEntity;
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
public class UserMapper {

    private static final String ROLE_PREFIX = "ROLE_";
    private final PasswordEncoder encoder;

    public UserDto mapToDto(UserEntity userEntity) {
        return UserDto.builder()
                .userName(userEntity.getUsername())
                .name(userEntity.getFullName())
                .email(userEntity.getEmail())
                .roles(userEntity.getRoles().stream()
                        .map(RoleEntity::getName)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public UserEntity mapToNewEntity(UserCreateRequest request, List<RoleEntity> roleEntities) {
        return UserEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .password(encoder.encode(request.getPassword()))
                .roles(roleEntities)
                .build();
    }

    public UserDetails mapToUserDetailsEntity(UserEntity user) {
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .authorities(getauthorities(user.getRoles()))
                .build();
    }

    private static Collection<? extends GrantedAuthority> getauthorities(Collection<RoleEntity> roles) {
        return roles.stream()
                .map(roleEntity -> new SimpleGrantedAuthority(ROLE_PREFIX + roleEntity.getName()))
                .collect(Collectors.toList());
    }

    public void mapUpdatedEntity(UserEntity entity, UserUpdateRequest userUpdateRequest) {
        entity.setFullName(userUpdateRequest.getFullName());
        entity.setEmail(userUpdateRequest.getEmail());
    }
}
