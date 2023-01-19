package mlab.order.management.service.user;

import lombok.RequiredArgsConstructor;
import mlab.order.management.exception.BadRequestException;
import mlab.order.management.exception.RecordNotFoundException;
import mlab.order.management.model.dto.UserDto;
import mlab.order.management.model.entity.RoleEntity;
import mlab.order.management.model.entity.UserEntity;
import mlab.order.management.model.request.user.UserCreateRequest;
import mlab.order.management.model.request.user.UserUpdateRequest;
import mlab.order.management.repository.RoleRepository;
import mlab.order.management.repository.UserRepository;
import mlab.order.management.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseService implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper mapper;

    @Override
    public UserDto createUser(UserCreateRequest request) {
        List<RoleEntity> roleEntities = roleRepository.findByNameIn(request.getRoles());
        userRepository.findDistinctByUsername(request.getUsername())
                .ifPresent(userEntity -> {
                    throw new BadRequestException(getLocaleMessage("api.user.exist"));
                });

        UserEntity userEntity = mapper.mapToNewEntity(request, roleEntities);
        userRepository.save(userEntity);
        return mapper.mapToDto(userEntity);
    }

    @Override
    public UserDto updateUser(UserUpdateRequest userUpdateRequest) {
        UserEntity user = userRepository.findById(userUpdateRequest.getId())
                .orElseThrow(() -> {
                    throw new RecordNotFoundException(getLocaleMessage("api.user.notFound"));
                });
        mapper.mapUpdatedEntity(user, userUpdateRequest);
        userRepository.save(user);
        return mapper.mapToDto(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(long id) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(getLocaleMessage("api.user.notFound")));
        return mapper.mapToDto(userEntity);
    }

    @Override
    public UserDto delete(long id) {
        UserEntity user = userRepository.findById(id)
                .map(userEntity -> {
                    userEntity.getRoles().forEach(role -> role.getUsers().remove(userEntity));
                    userEntity.getRoles().clear();
                    userEntity.getOrders().forEach(order -> order.setUser(null));
                    userEntity.getOrders().clear();
                    userRepository.delete(userEntity);
                    return userEntity;
                }).orElseThrow(() -> new RecordNotFoundException(getLocaleMessage("api.user.notFound")));
        return mapper.mapToDto(user);
    }

    public Optional<UserEntity> findUserByUsername(String userName){
        return userRepository.findDistinctByUsername(userName);
    }

}
