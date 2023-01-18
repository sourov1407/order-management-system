package mlab.order.management.service.user;

import mlab.order.management.model.dto.UserDto;
import mlab.order.management.model.entity.UserEntity;
import mlab.order.management.model.request.user.UserCreateRequest;
import mlab.order.management.model.request.user.UserUpdateRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserService {

    @Transactional
    UserDto createUser(UserCreateRequest request);

    @Transactional
    UserDto updateUser(UserUpdateRequest userUpdateRequest);

    @Transactional
    List<UserDto> getAllUser();

    @Transactional
    UserDto getUser(long id);

    @Transactional
    UserDto delete(long id);

    @Transactional
    Optional<UserEntity> findUserByUsername(String userName);

}
