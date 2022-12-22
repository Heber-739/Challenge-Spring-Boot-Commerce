package com.challenge.Repository;

import com.challenge.Model.Client;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    public List<Client> findAllByEnabled(boolean enabled);

    public boolean existsById(int id);

}
