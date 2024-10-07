package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.dto.client.ClientPasswordResponseDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientRequestDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Client;
import co.edu.uniquindio.unieventos.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService{

    @Autowired
    private ClientRepository clientRepository;


    // Método para convertir de DTO a entidad
    private Client convertToEntity(ClientRequestDTO dto) {

        return Client.builder()
                .firstName(dto.firstName())
                .id(dto.id())
                .lastName(dto.lastName())
                .email(dto.email())
                .phoneNumber(dto.phoneNumber())
                .address(dto.address())
                .password(dto.password())
                .build();
    }

    // Método para convertir de entidad a DTO
    private ClientResponseDTO convertToDTO(Client client) {

        return new ClientResponseDTO(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getEmail(),
                client.getPhoneNumber(),
                client.getPassword()
        );
    }


    //Metodo para convertir cliente a dto para ver la contraseña
    private ClientPasswordResponseDTO convertToPasswordDTO(Client client) {

        return new ClientPasswordResponseDTO(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getPassword()
        );
    }

    public List<ClientResponseDTO> findByFirtsName(String firtsName) {
        return clientRepository.findByFirstNameContainingIgnoreCase(firtsName).stream().
                map(this::convertToDTO).
                collect(Collectors.toList());
    }

    public List<ClientResponseDTO> findByLastName(String lastName) {
        return clientRepository.findByLastNameContainingIgnoreCase(lastName).stream().
                map(this::convertToDTO).
                collect(Collectors.toList());
    }

    public List<ClientResponseDTO> findByEmail(String email) {

        return clientRepository.findByEmailContainingIgnoreCase(email).stream().
                map(this::convertToDTO).
                collect(Collectors.toList());
    }

    public List<ClientResponseDTO> findByPhoneNumber(String phoneNumber) {
        return clientRepository.findByPhoneNumberContaining(phoneNumber).stream().
                map(this::convertToDTO).
                collect(Collectors.toList());
    }


    public ClientResponseDTO createClient(ClientRequestDTO clientRequestDTO){
        Client client1 = convertToEntity(clientRequestDTO);
        client1 = clientRepository.save(client1);
        return convertToDTO(client1);
    }


    public List<ClientResponseDTO> getAllClient(){
        return clientRepository.findAll().stream().
                map(this::convertToDTO).
                collect(Collectors.toList());
    }

    public List<ClientResponseDTO> findByFullName(String firstName, String lastName) {
        return clientRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName,lastName).stream().
                map(this::convertToDTO).
                collect(Collectors.toList());
    }

    public List<ClientResponseDTO> findByAnyName(String firstName, String lastName) {
        return clientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(firstName,lastName).stream().
                map(this::convertToDTO).
                collect(Collectors.toList());
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

    public Optional<ClientPasswordResponseDTO> getPasswordById(String id) {

        return clientRepository.findById(id)
                .map(this::convertToPasswordDTO);
    }

}
