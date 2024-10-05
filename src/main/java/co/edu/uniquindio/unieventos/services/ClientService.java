package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.model.document.Client;
import co.edu.uniquindio.unieventos.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
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

    public boolean deleteClientById(String clientId) {
        if (clientRepository.existsById(clientId)) {
            clientRepository.deleteById(clientId);
            return true;
        } else {
            throw new NoSuchElementException("Client with ID " + clientId + " does not exist.");
        }
    }

    public Client updateClient(String clientId, Client clientDetails) {
        // Verificar si el cliente existe
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Client with ID " + clientId + " not found"));

        // Actualizar los campos que deseas modificar
        existingClient.setFirstName(clientDetails.getFirstName());
        existingClient.setLastName(clientDetails.getLastName());
        existingClient.setEmail(clientDetails.getEmail());
        existingClient.setPhoneNumber(clientDetails.getPhoneNumber());
        existingClient.setAddress(clientDetails.getAddress());

        // Guardar los cambios
        return clientRepository.save(existingClient);
    }

    public boolean updatePassword(String clientId, String  newPassword) {
        // Verificar si el cliente existe
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Client with ID " + clientId + " not found"));

        // Actualizar contraseña
        existingClient.setPassword(newPassword);

        clientRepository.save(existingClient);
        // Guardar los cambios
        return true;
    }


}
