package com.challenge.Controller;

import com.challenge.Model.Provider;
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
@RequestMapping("/providers")
public class ProviderController {

    @Autowired
    ProviderService providerService;

    @GetMapping("/getAll")
    public List<Provider> getClients() {
        return providerService.getProviders();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getClient(@PathVariable("id") Long id) {
        if (!providerService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No existe el proveedor ingresado, por favor compruebe si el cuit es correcto"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Provider>(providerService.getOne(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createProvider(@RequestBody Provider provider) {
        try {
            Provider newProvider = new Provider();
            newProvider = provider;
            providerService.saveProvider(newProvider);
            return new ResponseEntity<Message>(new Message("Proveedor creado satifactoriamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Ocurrió un error inesperado: " + e), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProvider(@RequestBody Provider updateProvider) {
        if (!providerService.existById(updateProvider.getCuit())) {
            return new ResponseEntity<Message>(new Message("No existe el proveedor ingresado, por favor compruebe el campo dni"), HttpStatus.NOT_FOUND);
        }
        Provider provider = providerService.getOne(updateProvider.getCuit());
        try {
            provider.setCuit(updateProvider.getCuit());
            provider.setName(updateProvider.getName());
            provider.setPhone_number(updateProvider.getPhone_number());
            provider.setDirection(updateProvider.getDirection());
            providerService.saveProvider(provider);
            return new ResponseEntity<Message>(new Message("Proveedor Actualizado satifactoriamente"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Ocurrió un error inesperado: " + e), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteClient(@PathVariable("id") Long id) {
        if (!providerService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No existe el proveedor ingresado, por favor compruebe el campo dni"), HttpStatus.NOT_FOUND);
        }
        providerService.deleteProvider(id);
        return new ResponseEntity<Message>(new Message("Proveedor eliminado satifactoriamente"), HttpStatus.OK);

    }

}
