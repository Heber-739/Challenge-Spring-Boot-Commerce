package com.challenge.Controller;

import com.challenge.Model.Client;
import com.challenge.Service.ClientService;
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
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/getAll")
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getClient(@PathVariable("id") int id) {
        if (!clientService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No existe el cliente ingresado, por favor compruebe el campo dni"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Client>(clientService.getOne(id), HttpStatus.OK);
    }

    @PutMapping("/enabled/{id}")
    public ResponseEntity<Message> activatedClient(@PathVariable("id") int id) {
        if (!clientService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No existe el cliente ingresado, por favor compruebe el campo dni"), HttpStatus.NOT_FOUND);
        }
        Client client = clientService.getOne(id);
        if (client.isEnabled()) {
            return new ResponseEntity<Message>(new Message("El cliente ya se encuentra dado de alta en el sistema"), HttpStatus.BAD_REQUEST);
        }
        client.setEnabled(true);
        clientService.saveClient(client);
        return new ResponseEntity<Message>(new Message("Cliente activado satifactoriamente"), HttpStatus.OK);
    }

    @PutMapping("/disabled/{id}")
    public ResponseEntity<Message> disabledClient(@PathVariable("id") int id) {
        if (!clientService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No existe el cliente ingresado, por favor compruebe el campo dni"), HttpStatus.BAD_REQUEST);
        }
        Client client = clientService.getOne(id);
        if (!client.isEnabled()) {
            return new ResponseEntity<Message>(new Message("El cliente ya se encuentra dado de baja en el sistema"), HttpStatus.BAD_REQUEST);
        }
        client.setEnabled(false);
        clientService.saveClient(client);
        return new ResponseEntity<Message>(new Message("Cliente dado de baja satifactoriamente"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Message> createClient(@RequestBody Client client) {
        try {
            Client newClient = new Client();
            newClient = client;
            clientService.saveClient(client);
            return new ResponseEntity<Message>(new Message("Cliente creado satifactoriamente"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Ocurrió un error inesperado: " + e), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateClient(@RequestBody Client updateClient) {
        if (!clientService.existById(updateClient.getDni())) {
            return new ResponseEntity<Message>(new Message("No existe el cliente ingresado, por favor compruebe el campo dni"), HttpStatus.NOT_FOUND);
        }
        Client client = clientService.getOne(updateClient.getDni());
        try {
            client.setEnabled(updateClient.isEnabled());
            client.setName(updateClient.getName());
            client.setSurname(updateClient.getSurname());
            client.setPhone_number(updateClient.getPhone_number());
            client.setDirection(updateClient.getDirection());
            clientService.saveClient(client);
            return new ResponseEntity<Message>(new Message("Cliente actualizado satifactoriamente"), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<Message>(new Message("Ocurrió un error inesperado: " + e), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deleteClient(@PathVariable("id") int id) {
        if (!clientService.existById(id)) {
            return new ResponseEntity<Message>(new Message("No existe el cliente ingresado, por favor compruebe el campo dni"), HttpStatus.BAD_REQUEST);
        }
        clientService.deleteClient(id);
        return new ResponseEntity<Message>(new Message("Cliente eliminado satifactoriamente"), HttpStatus.OK);

    }
}
