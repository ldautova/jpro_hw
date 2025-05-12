package org.example.web;

import lombok.RequiredArgsConstructor;
import org.example.service.ProductService;
import org.example.web.dto.ProductDto;
import org.example.web.mapper.MapperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ProductController.
 *
 * @author Lina_Dautova
 */
@RequiredArgsConstructor
@RestController
public class ProductController {
    private final ProductService productService;
    private final MapperService productMapper;

    @GetMapping("/products")
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts().stream()
                .map(productMapper::toProductDto)
                .toList();
    }

    @GetMapping("/products/{productId}")
    public ProductDto getProductById(@PathVariable(name = "productId") Long productId) {
        return productMapper.toProductDto(productService.findByProductId(productId));
    }
}
