package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.model.document.Client;
import co.edu.uniquindio.unieventos.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService{

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> findByFirtsName(String firtsName) {
        return clientRepository.findByFirstNameContainingIgnoreCase(firtsName);
    }

    public List<Client> findByLastName(String lastName) {
        return clientRepository.findByLastNameContainingIgnoreCase(lastName);
    }

    public List<Client> findByEmail(String email) {
        return clientRepository.findByEmailContainingIgnoreCase(email);
    }

    public List<Client> findByPhoneNumber(String phoneNumber) {return clientRepository.findByPhoneNumberContaining(phoneNumber);}


    public Client createClient(Client client){return clientRepository.save(client);}


    public List<Client> getAllClient(){return clientRepository.findAll();}

    public Optional<Client> findByFullName(String firstName, String lastName) {
        return clientRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName, lastName);
    }

    public List<Client> findByAnyName(String firstName, String lastName) {
        return clientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstName,lastName);
    }
}
