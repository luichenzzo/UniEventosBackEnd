package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.dto.client.*;
import co.edu.uniquindio.unieventos.dto.event.EventRequestDTO;
import co.edu.uniquindio.unieventos.dto.event.EventResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Admin;
import co.edu.uniquindio.unieventos.model.document.Client;
import co.edu.uniquindio.unieventos.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@CrossOrigin(origins = "http//localhost:3000/login")
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;



    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        System.out.println(clientRequestDTO.firstName());
        ClientResponseDTO clientResponseDTO = clientService.createClient(clientRequestDTO);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @GetMapping("/findByFirstName")
    public ResponseEntity<?> getByFirstName(@RequestParam String firstName) {

        List<ClientResponseDTO> client = clientService.findByFirtsName(firstName);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllClients() {

        List<ClientResponseDTO> client = clientService.getAllClient();
        return ResponseEntity.ok(client);
    }

    @GetMapping("/findByLastName")
    public ResponseEntity<?> getByLastName(@RequestParam String lastName) {

        List<ClientResponseDTO> client = clientService.findByLastName(lastName);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> getByEmail(@RequestParam  String email) {

        List<ClientResponseDTO> client = clientService.findByEmail(email);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/findByPhoneNumber")
    public ResponseEntity<?> getByPhoneNumber(@RequestParam String phoneNumber) {

        List<ClientResponseDTO> client = clientService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(client);
    }


    @GetMapping("/findByFullName")
    public List<ClientResponseDTO> getByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        return clientService.findByFullName(firstName, lastName);
    }

    @GetMapping("/findByAnyName")
    public List<ClientResponseDTO> getByAnyName(@RequestParam String firstName, @RequestParam String lastName) {
        return clientService.findByAnyName(firstName, lastName);
    }

    @DeleteMapping("/delete")
    public boolean delete(@RequestParam String id)
    {
        return  clientService.deleteClientById(id);
    }


    @PutMapping("/update")
    public ResponseEntity<ClientResponseDTO> updateClient(@RequestParam String id, @RequestBody ClientRequestDTO clientRequestDTO)
    {
        if (!clientService.findByID(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        ClientResponseDTO clientResponseDTO = clientService.updateClient(id , clientRequestDTO);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @PutMapping("/updatePassword")
    public ResponseEntity<ClientPasswordResponseDTO> updatePassword(@RequestParam String id, @RequestBody ClientPasswordResponseDTO clientRequestDTO)
    {
        ClientPasswordResponseDTO clientResponsetDTO = clientService.updatePassword(id, clientRequestDTO);
        return ResponseEntity.ok(clientRequestDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<?> iniciarSesion(@RequestBody ClientLoginRequestDTO loginDTO) {
        try {
            System.out.println("entro " + loginDTO.email());
            String token = clientService.iniciarSesion(loginDTO.email(), loginDTO.password());
            System.out.println(loginDTO.email());
            System.out.println(loginDTO.password());

            if(token != null)
            {
                return ResponseEntity.ok(new JwtResponse(token));
            }
            else {
                return ResponseEntity.status(401).body("Credenciales inválidas");
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Credenciales inválidas");
        }
    }

    @PostMapping("/likeSong")
    public ResponseEntity<?> likeSong(){

    }
    public record JwtResponse(String token) {}





}

