package com.challenge.Service;

import com.challenge.Model.Sale;
import com.challenge.Repository.SaleRepository;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaleService {

    @Autowired
    SaleRepository saleRepository;

    public List<Sale> getSales() {
        return saleRepository.findAll();
    }

    public Sale getOne(int id) {
        return saleRepository.findById(id).orElse(null);
    }

    public List<Sale> getByDate(Date registDate) {
        return saleRepository.findAllSalesByDate(registDate);
    }

    public boolean existById(int id) {
        return saleRepository.existsById(id);
    }

    public void saveSale(Sale sale) {
        saleRepository.save(sale);
    }

    public void deleteSale(int id) {
        saleRepository.deleteById(id);
    }

}
