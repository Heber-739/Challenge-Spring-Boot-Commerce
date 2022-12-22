package com.challenge.Service;

import com.challenge.Model.Product;
import com.challenge.Repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByStock(int min_stock) {
        return productRepository.findAllProductsByStockAfter(min_stock);
    }

    public Product getOne(int id) {
        return productRepository.findById(id).orElse(null);
    }

    public boolean existById(int id) {
        return productRepository.existsById(id);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }
}
