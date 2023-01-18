package mlab.order.management.service.auth;

import mlab.order.management.model.request.auth.AuthRequest;
import mlab.order.management.model.response.auth.AuthResponse;
import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {

    AuthResponse authenticate(AuthRequest request);

    UserDetails validateToken(String token);
}
