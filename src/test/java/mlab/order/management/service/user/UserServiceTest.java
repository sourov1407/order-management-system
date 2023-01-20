package mlab.order.management.service.user;

import mlab.order.management.model.dto.UserDto;
import mlab.order.management.model.entity.RoleEntity;
import mlab.order.management.model.entity.UserEntity;
import mlab.order.management.model.request.user.UserCreateRequest;
import mlab.order.management.repository.RoleRepository;
import mlab.order.management.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestPropertySource("classpath:application.yml")
public class UserServiceTest {

    private UserService userService;
    @MockBean
    private RoleRepository roleRepository;
    @MockBean
    private UserRepository userRepository;
    @MockBean
    private UserMapper userMapper;

    @BeforeEach
    void init() {
        userService = new UserServiceImpl(userRepository,roleRepository,userMapper);
    }

    @Test
    void createUserTest(){
        RoleEntity roleEntity = RoleEntity.builder()
                .id(1)
                .name("USER")
                .build();

        UserDto userDto = UserDto.builder()
                .userName("test username")
                .email("test@mail.com")
                .name("test name")
                .roles(Arrays.asList("USER"))
                .build();

        when(roleRepository.findByNameIn(anyList())).thenReturn(Collections.singletonList(roleEntity));
        when(userRepository.findDistinctByUsername(anyString())).thenReturn(Optional.empty());
        when(userMapper.mapToNewEntity(any(),anyList())).thenReturn(new UserEntity());
        when(userMapper.mapToDto(any())).thenReturn(userDto);
        when(userRepository.save(any())).thenReturn(any());

        UserDto result = userService.createUser(new UserCreateRequest());

        assertEquals(result.getUserName(), "test username");
        assertEquals(result.getEmail(), "test@mail.com");
        assertEquals(result.getName(), "test name");
        assertEquals(result.getRoles(), Collections.singletonList("USER"));

        verify(userRepository, times(1)).save(any());

    }
}
