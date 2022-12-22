package com.challenge.Controller;

import com.challenge.Dto.ProductDto;
import com.challenge.Model.Product;
import com.challenge.Model.Provider;
import com.challenge.Service.ProductService;
import com.challenge.Service.ProviderService;
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
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProviderService providerService;

    @GetMapping("/getAll")
    public List<Product> getClients() {
        return productService.getProducts();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getClient(@PathVariable("id") int id) {
        if (!productService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No existe el producto que trata de buscar"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(productService.getOne(id), HttpStatus.OK);
    }

    @GetMapping("/getByStock/{stock}")
    public List<Product> getByStock(@PathVariable int stock) {
        return productService.getProductsByStock(stock);
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<Message> createProduct(@PathVariable("id") Long provider_id, @RequestBody ProductDto product) {
        try {
            Product newProduct = new Product();
            Provider pr = providerService.getOne(provider_id);
            newProduct.setName(product.getName());
            newProduct.setDescription(product.getDescription());
            newProduct.setPrice(product.getPrice());
            newProduct.setStock(product.getStock());
            pr.addProduct(newProduct);

            productService.saveProduct(newProduct);
            return new ResponseEntity<Message>(new Message("Producto creado correctamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Ocurrió un error inesperado: " + e), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable("id") Long provider_id, @RequestBody Product product) {
        if (!productService.existById(product.getId())) {
            return new ResponseEntity<Message>(new Message("No existe el producto que trata de buscar"), HttpStatus.NOT_FOUND);
        }
        try {
            Product db_product = productService.getOne(product.getId());
            db_product.setName(product.getName());
            db_product.setDescription(product.getDescription());
            db_product.setPrice(product.getPrice());
            db_product.setStock(product.getStock());
            db_product.setProvider(providerService.getOne(provider_id));

            productService.saveProduct(db_product);
            return new ResponseEntity<Message>(new Message("Producto actualizado correctamente"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Ocurrió un error inesperado: " + e), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteClient(@PathVariable("id") int id) {
        if (!productService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No se encuentra el producto que trata de eliminar"), HttpStatus.NOT_FOUND);
        }
        productService.deleteProduct(id);
        return new ResponseEntity<Message>(new Message("Producto eliminado correctamente"), HttpStatus.OK);

    }
}
