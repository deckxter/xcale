package org.xcale.service;

import org.springframework.stereotype.Service;
import org.xcale.entity.Product;
import org.xcale.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> saveAll(List<Product> productsToSave) {
        List<Product> toReturn = new ArrayList<>();

        for(Product productToSave : productsToSave) {
            Product productSaved = productRepository.save(productToSave);
            toReturn.add(productSaved);
        }
        return toReturn;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findByIdProduct(Long idProduct) {
        return productRepository.findById(idProduct);
    }

}
