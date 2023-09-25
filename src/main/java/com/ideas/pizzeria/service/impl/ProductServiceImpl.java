package com.ideas.pizzeria.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ideas.pizzeria.dto.ProductDto;
import com.ideas.pizzeria.handler.exception.ResourceNotFoundException;
import com.ideas.pizzeria.model.Product;
import com.ideas.pizzeria.repository.ProductRepository;
import com.ideas.pizzeria.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;
    private final ObjectMapper mapper;

    @Override
    public ProductDto createProduct(ProductDto productoDto) {
        Product newProduct = mapper.convertValue(productoDto, Product.class);
        productRepository.save(newProduct);
        return mapper.convertValue(newProduct, ProductDto.class);
    }

    @Override
    public ProductDto findProductById(Long productId) throws ResourceNotFoundException {
        Product productToFind = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return mapper.convertValue(productToFind, ProductDto.class);
    }

    @Override
    public void updateProduct(ProductDto productDto) throws ResourceNotFoundException {
        Product productToModify = productRepository.findById(productDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        mapper.convertValue(productRepository.save(productToModify), ProductDto.class);
    }

    @Override
    public void deleteProduct(Long productId) throws ResourceNotFoundException {
        Product productToDelete = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        productRepository.delete((productToDelete));
    }

    @Override
    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductDto> porductDtoList = products
                .stream()
                .map(product -> mapper.convertValue(product, ProductDto.class))
                .collect(Collectors.toList());
        return porductDtoList;
    }
}
