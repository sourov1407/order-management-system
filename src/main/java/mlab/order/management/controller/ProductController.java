package mlab.order.management.controller;

import lombok.RequiredArgsConstructor;
import mlab.order.management.exception.BadRequestException;
import mlab.order.management.model.common.CommonResponse;
import mlab.order.management.model.dto.ProductDto;
import mlab.order.management.model.request.product.ProductCreateRequest;
import mlab.order.management.model.request.product.ProductUpdateRequest;
import mlab.order.management.service.product.ProductService;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static mlab.order.management.util.ResponseBuilder.getSuccessResponse;

@RestController
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("/products")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<ProductDto>> createProduct(@RequestBody @Valid ProductCreateRequest request,
                                                                    BindingResult bindingResult) {
        throwIfError(bindingResult);
        return ResponseEntity.ok(getSuccessResponse(productService.createProduct(request)));
    }

    @PutMapping("/products")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<ProductDto>> updateProduct(@RequestBody @Valid ProductUpdateRequest request,
                                                              BindingResult bindingResult) {
        throwIfError(bindingResult);
        return ResponseEntity.ok(getSuccessResponse(productService.updateProduct(request)));
    }

    @GetMapping("/products")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<List<ProductDto>>> getAll() {
        return ResponseEntity.ok(getSuccessResponse(productService.getAllProduct()));
    }

    @GetMapping("/products/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<ProductDto>> getProduct(@PathVariable long id) {
        return ResponseEntity.ok(getSuccessResponse(productService.getProduct(id)));
    }

    @DeleteMapping("/products/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CommonResponse<ProductDto>> deleteUser(@PathVariable long id) {
        return ResponseEntity.ok(getSuccessResponse(productService.deleteProduct(id)));
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
