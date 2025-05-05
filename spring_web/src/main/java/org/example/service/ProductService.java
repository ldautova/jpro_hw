package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ProductService.
 *
 * @author Lina_Dautova
 */
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findByProductId(Long productId) {
        return productRepository.findById(productId);
    }
}
