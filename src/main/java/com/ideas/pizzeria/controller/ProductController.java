package com.ideas.pizzeria.controller;

import com.ideas.pizzeria.dto.ProductDto;
import com.ideas.pizzeria.handler.exception.ResourceNotFoundException;
import com.ideas.pizzeria.service.impl.ProductServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.ideas.pizzeria.handler.ResponseBuilder.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductServiceImpl productService;

    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productoDto) {
        productService.createProduct(productoDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long productId) throws ResourceNotFoundException {
        return responseBuilder(HttpStatus.OK, productService.findProductById(productId));
    }

    @PutMapping("/update")
    public ResponseEntity<?> modifyProduct(@RequestBody ProductDto productDto) throws ResourceNotFoundException {
        productService.updateProduct(productDto);
        return responseBuilder(HttpStatus.OK, productDto);
    }

    @DeleteMapping("{id}")
    public void deleteProduct(@PathVariable("id") Long productId) throws ResourceNotFoundException {
        productService.deleteProduct(productId);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllProducts() {
        return responseBuilder(HttpStatus.OK, productService.listProducts());
    }
}
