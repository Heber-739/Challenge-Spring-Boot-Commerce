package com.challenge.Controller;

import com.challenge.Dto.SaleDto;
import com.challenge.Model.Product;
import com.challenge.Model.Sale;
import com.challenge.Service.ClientService;
import com.challenge.Service.ProductService;
import com.challenge.Service.SaleService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SaleController {
    
    @Autowired
    SaleService saleService;
    
    @Autowired
    ClientService clientService;
    
    @Autowired
    ProductService productService;
    
    @GetMapping("/getAll")
    public List<Sale> getSaless() {
        return saleService.getSales();
    }
    
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSale(@PathVariable("id") int id) {
        if (!saleService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No existe el registro de la venta que trata de buscar"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Sale>(saleService.getOne(id), HttpStatus.OK);
    }
    
    @GetMapping("/getByDate/{date}")
    public ResponseEntity<?> getByDate(@PathVariable("date") String dateString) {
        try {
            Date date = new SimpleDateFormat("yyyy/MM/dd").parse(dateString);
            return new ResponseEntity<List<Sale>>(saleService.getByDate(date), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Campo de fecha invalido"), HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping("/create")
    public ResponseEntity<Message> createSale(@RequestBody SaleDto sale) {
        Product product = productService.getOne(sale.getProduct_id());
        if (!clientService.existById(sale.getClient_dni())) {
            return new ResponseEntity<Message>(new Message("No existe el cliente vinculado, revise el dni ingresado"), HttpStatus.NOT_FOUND);
        }
        if (product.getStock() >= sale.getUnits()) {
            try {
                
                Sale newSale = new Sale();
                Date date = new SimpleDateFormat("yyyy/MM/dd").parse(sale.getRegistDate());
                newSale.setDate(date);
                newSale.setClient(clientService.getOne(sale.getClient_dni()));
                newSale.setProduct_id(sale.getProduct_id());
                newSale.setUnits(sale.getUnits());
                newSale.setTotal_price(sale.getTotal_price());
                
                saleService.saveSale(newSale);
                return new ResponseEntity<Message>(new Message("Registro de venta creado satifactoriamente"), HttpStatus.OK);
            } catch (ParseException e) {
                return new ResponseEntity<Message>(new Message("Ocurrió un error inesperado: " + e), HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<Message>(new Message("No existe stock suficiente para la venta de este producto"), HttpStatus.BAD_REQUEST);
        }
        
    }
    
    @PutMapping("/update/{clientId}/{saleId}")
    public ResponseEntity<?> updateProvider(@PathVariable("saleId") int sale_id, @PathVariable("clientId") int client_id, @RequestBody SaleDto updateSale) {
        if (!clientService.existById(client_id)) {
            return new ResponseEntity<Message>(new Message("No existe el cliente ingresado"), HttpStatus.NOT_FOUND);
        }
        if (!saleService.existById(sale_id)) {
            return new ResponseEntity<Message>(new Message("No se encuentra el registro de la venta"), HttpStatus.NOT_FOUND);
        }
        Sale sale = saleService.getOne(sale_id);
        try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(updateSale.getRegistDate());
            sale.setDate(date);
            sale.setClient(clientService.getOne(updateSale.getClient_dni()));
            sale.setUnits(updateSale.getUnits());
            sale.setTotal_price(updateSale.getTotal_price());
            
            saleService.saveSale(sale);
            return new ResponseEntity<Message>(new Message("Registro de venta actualizada satifactoriamente"), HttpStatus.OK);
            
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Ocurrió un error inesperado: " + e), HttpStatus.BAD_REQUEST);
        }
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteClient(@PathVariable("id") int id) {
        if (!saleService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No existe el registro de venta"), HttpStatus.BAD_REQUEST);
        }
        saleService.deleteSale(id);
        return new ResponseEntity<Message>(new Message("Registro de venta eliminado satifactoriamente"), HttpStatus.OK);
        
    }
    
}
