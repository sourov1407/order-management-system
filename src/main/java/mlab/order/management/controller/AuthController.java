package mlab.order.management.controller;

import lombok.RequiredArgsConstructor;
import mlab.order.management.model.request.auth.AuthRequest;
import mlab.order.management.model.response.auth.AuthResponse;
import mlab.order.management.service.auth.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
