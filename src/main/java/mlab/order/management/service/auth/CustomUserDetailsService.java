package mlab.order.management.service.auth;

import lombok.RequiredArgsConstructor;
import mlab.order.management.exception.RecordNotFoundException;
import mlab.order.management.model.entity.UserEntity;
import mlab.order.management.service.user.UserService;
import mlab.order.management.service.user.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    @Lazy
    private UserService userService;

    @Autowired
    @Lazy
    private UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userService
                .findUserByUsername(username)
                .orElseThrow(()-> new RecordNotFoundException("User not found"));

        return mapper.mapToUserDetailsEntity(user);

    }

}
