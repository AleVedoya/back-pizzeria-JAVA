package com.ideas.pizzeria.service;

import com.ideas.pizzeria.dto.ProductDto;
import com.ideas.pizzeria.handler.exception.ResourceNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IProductService {

    ProductDto createProduct(ProductDto productoDto);
    ProductDto findProductById(Long productId) throws ResourceNotFoundException;
    void updateProduct(ProductDto productDto) throws ResourceNotFoundException;
    void deleteProduct(Long productId) throws ResourceNotFoundException;
    List<ProductDto> listProducts();
//    List<ProductDto>getProductsByCategory(Long categoryId);
}
