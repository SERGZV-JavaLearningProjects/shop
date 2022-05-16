package ru.shop.three_d_print.service;

import org.springframework.stereotype.Service;
import ru.shop.three_d_print.entities.Product;
import ru.shop.three_d_print.repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService
{
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) { this.productRepository = productRepository; }

    public Optional<Product> findById(Long id)
    {
        return productRepository.findById(id);
    }
}
