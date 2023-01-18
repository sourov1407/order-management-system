package mlab.order.management.controller;

import lombok.RequiredArgsConstructor;
import mlab.order.management.exception.BadRequestException;
import mlab.order.management.model.common.CommonResponse;
import mlab.order.management.model.dto.OrderDto;
import mlab.order.management.model.dto.ProductDto;
import mlab.order.management.model.dto.UserDto;
import mlab.order.management.model.request.order.OrderCreateRequest;
import mlab.order.management.model.request.user.UserCreateRequest;
import mlab.order.management.model.request.user.UserUpdateRequest;
import mlab.order.management.service.order.OrderService;
import mlab.order.management.service.product.ProductService;
import mlab.order.management.service.user.UserService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static mlab.order.management.util.ResponseBuilder.getSuccessResponse;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final OrderService orderService;
    private final ProductService productService;

    @PostMapping("/users")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<UserDto>> createUser(@RequestBody @Valid UserCreateRequest userCreateRequest,
                                                              BindingResult bindingResult) {
        throwIfError(bindingResult);
        return ResponseEntity.ok(getSuccessResponse(userService.createUser(userCreateRequest)));
    }

    @PutMapping("/users")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<UserDto>> updateUser(@RequestBody @Valid UserUpdateRequest userUpdateRequest,
                                                              BindingResult bindingResult) {
        throwIfError(bindingResult);
        return ResponseEntity.ok(getSuccessResponse(userService.updateUser(userUpdateRequest)));
    }

    @GetMapping("/users")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<List<UserDto>>> getAll() {
        return ResponseEntity.ok(getSuccessResponse(userService.getAllUser()));
    }

    @GetMapping("/users/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<UserDto>> getUser(@PathVariable long id) {
        return ResponseEntity.ok(getSuccessResponse(userService.getUser(id)));
    }

    @DeleteMapping("/users/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<UserDto>> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok(getSuccessResponse(userService.delete(id)));
    }

    @PostMapping("/users/order")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<OrderDto>> createOrder(@RequestBody @Valid OrderCreateRequest request,
                                                                BindingResult bindingResult) {
        throwIfError(bindingResult);
        return ResponseEntity.ok(getSuccessResponse(orderService.createOrder(request)));
    }

    @GetMapping("/users/search/product")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<List<ProductDto>>> searchProduct(@RequestParam(value = "name", required = false) String name,
                                                                          @RequestParam(value = "sku", required = false) String sku,
                                                                          @RequestParam(value = "category",required = false) String category,
                                                                          Pageable pageable) {
        return ResponseEntity.ok(getSuccessResponse(productService.searchProduct(name, sku, category, pageable)));
    }

    @GetMapping("/users/search/order")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<List<OrderDto>>> searchOrder(@RequestParam(value = "productName", required = false) String name,
                                                                      @RequestParam(value = "category", required = false) String category,
                                                                      Pageable pageable) {
        return ResponseEntity.ok(getSuccessResponse(orderService.searchOrder(name,category,pageable)));
    }

    @GetMapping("/users/search/orders/{id}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<CommonResponse<List<OrderDto>>> searchOrderByUser(@PathVariable("id") long id) {
        return ResponseEntity.ok(getSuccessResponse(orderService.searchOrdersByUser(id)));
    }

    private void throwIfError(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(getErrors(bindingResult)
            );
        }
    }

    private String getErrors(BindingResult bindingResult) {
        return bindingResult.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
    }

}
