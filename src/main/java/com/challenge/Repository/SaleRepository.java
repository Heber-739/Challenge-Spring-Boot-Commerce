package com.challenge.Repository;

import com.challenge.Model.Sale;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Integer> {

    public List<Sale> findAllSalesByDate(Date registDate);

}
