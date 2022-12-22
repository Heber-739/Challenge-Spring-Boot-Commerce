package com.challenge.Service;

import com.challenge.Model.Client;
import com.challenge.Repository.ClientRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ClientService {
    
    @Autowired
    ClientRepository clientRepository;
    
    public List<Client> getClients() {
        return clientRepository.findAllByEnabled(true);
    }
    
    public Client getOne(int id) {
        return clientRepository.findById(id).orElse(null);
    }
    
    public boolean existById(int id) {
        return clientRepository.existsById(id);
    }
    
    public void saveClient(Client client) {
        clientRepository.save(client);
    }
    
    public void deleteClient(int id) {
        clientRepository.deleteById(id);
    }
}
