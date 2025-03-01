package FashionFlow.product_service.controller;

import FashionFlow.product_service.dto.ProductRequest;
import FashionFlow.product_service.dto.ProductResponse;
import FashionFlow.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
//        we are using this thread to check the circuit breaker works
//        try{
//            Thread.sleep(3000);
//        } catch (InterruptedException e){
//            throw new RuntimeException(e);
//        }
        return productService.getAllProducts();
    }
}