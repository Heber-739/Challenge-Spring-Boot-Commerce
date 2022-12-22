package com.challenge.Repository;

import com.challenge.Model.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    public List<Product> findAllProductsByStockAfter(int min_stock);

}
