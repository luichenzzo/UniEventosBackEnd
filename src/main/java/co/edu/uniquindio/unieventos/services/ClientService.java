package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.config.JWTUtils;
import co.edu.uniquindio.unieventos.dto.client.ClientLoginRequestDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientPasswordResponseDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientRequestDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Client;
import co.edu.uniquindio.unieventos.repositories.ClientRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService{

    @Autowired
    private ClientRepository clientRepository;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private String jwtExpiration;


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

    public ClientResponseDTO updateClient(String clientId, ClientRequestDTO clientRequestDTO) {

        Client client = convertToEntity(clientRequestDTO);
        client = clientRepository.save(client);
        return convertToDTO(client);

    }

    public ClientPasswordResponseDTO updatePassword(String clientId, ClientPasswordResponseDTO clientPasswordResponseDTO) {
        // Verificar si el cliente existe
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new NoSuchElementException("Client with ID " + clientId + " not found"));

        // Actualizar contraseña
        if(clientId.equals(clientPasswordResponseDTO.id())){
            existingClient.setPassword(clientPasswordResponseDTO.token());
        }

        clientRepository.save(existingClient);
        return convertToPasswordDTO(existingClient);
        // Guardar los cambios

    }

    public Optional<ClientPasswordResponseDTO> getPasswordById(String id) {

        return clientRepository.findById(id)
                .map(this::convertToPasswordDTO);
    }

    public Optional<ClientResponseDTO> findByID(String id) {
        return clientRepository.getClientById(id);
    }

    public String iniciarSesion(String email, String password) {
        Client client = clientRepository.findByEmailAndPassword(email, password);
        if (client != null) {
            return generarToken(client);
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }

    private String generarToken(Client client) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", client.getId());
        claims.put("email", client.getEmail());
        claims.put("firstName", client.getFirstName());
        claims.put("lastName", client.getLastName());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(client.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }




}
