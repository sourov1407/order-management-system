package mlab.order.management;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mlab.order.management.model.request.product.ProductCreateRequest;
import mlab.order.management.model.request.user.UserCreateRequest;
import mlab.order.management.service.product.ProductService;
import mlab.order.management.service.role.RoleService;
import mlab.order.management.service.user.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class AppSeeder implements ApplicationListener<ContextRefreshedEvent> {

    public static final String TRUE = "true";

    @Value("${app.seeder.enable}")
    private String isSeederEnabled;

    private final RoleService roleService;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if(isSeederEnabled.equals(TRUE)){
            populateRoles();
            populateUsers();
            populateProduct();
        }
    }

    private void populateProduct() {
        if(CollectionUtils.isEmpty(productService.getAllProduct()))
            productService.createProduct(
                    new ProductCreateRequest(
                            "test product",
                            "123",
                            "test category",
                            100,
                            "01-10-2023"));
    }

    private void populateUsers() {
        if(CollectionUtils.isEmpty(userService.getAllUser()))
            userService.createUser(
                    new UserCreateRequest(
                            "admin",
                            "admin",
                            "Admin",
                            "admin@gmail.com",
                            List.of(
                                    "ADMIN",
                                    "USER"
                            )
                    )
            );
    }

    private void populateRoles() {
        if(CollectionUtils.isEmpty(roleService.findAll()))
            roleService.createRoles(List.of(
                    "ADMIN",
                    "USER"
            ));
    }}
