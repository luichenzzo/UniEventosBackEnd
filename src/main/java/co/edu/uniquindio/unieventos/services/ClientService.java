package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.model.document.Client;
import co.edu.uniquindio.unieventos.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService{

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findByFirtsName(String firtsName) {
        return clientRepository.findByFirstName(firtsName);
    }

    public List<Client> findByLastName(String lastName) {
        return clientRepository.findByLastName(lastName);
    }

    public List<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public List<Client> findByPhoneNumber(String phoneNumber) {return clientRepository.findByPhoneNumber(phoneNumber);}


    public Client createClient(Client client){return clientRepository.save(client);}
}
