package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.dto.client.ClientLoginRequestDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientPasswordResponseDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientRequestDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Client;
import co.edu.uniquindio.unieventos.repositories.ClientRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService{

    @Autowired
    private ClientRepository clientRepository;

    private final String SECRET_KEY = "miClaveSecreta1111111111111111111111zunizteamo455"; // Asegúrate de cambiar esto y mantenerlo seguro
    private final long EXPIRATION_TIME = 2000000; //


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

    public String loginCliente(ClientLoginRequestDTO clientLoginRequestDTO) {


        // Buscar el cliente por su email
        Client client = clientRepository.findByEmail(clientLoginRequestDTO.email());
        if(client.getPassword().equals(clientLoginRequestDTO.password())){
            // Si el login es exitoso, generar un token (puedes usar JWT o alguna otra lógica)
            String token = generateTokenClient(clientLoginRequestDTO.email());
            System.out.println("token" + token);
            return token; // Retornamos el token generado
        }
        else{
            System.out.println("Usuario o contraseña invalidas");
            return null;
        }


    }

    private String generateTokenClient(String email) {

        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", "Client"); // Puedes agregar roles u otros datos relevantes aquí
        // Generar el token
         String token = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis())) // Fecha de emisión
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fecha de expiración
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY) // Firmar el token
                .compact();

         return token;
    }




}
