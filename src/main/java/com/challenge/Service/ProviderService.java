package com.challenge.Service;

import com.challenge.Model.Provider;
import com.challenge.Repository.ProviderRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProviderService {

    @Autowired
    ProviderRepository providerRepository;

    public List<Provider> getProviders() {
        return providerRepository.findAll();
    }

    public Provider getOne(Long id) {
        return providerRepository.findById(id).orElse(null);
    }

    public boolean existById(Long id) {
        return providerRepository.existsById(id);
    }

    public void saveProvider(Provider provider) {
        providerRepository.save(provider);
    }

    public void deleteProvider(Long id) {
        providerRepository.deleteById(id);
    }
}
