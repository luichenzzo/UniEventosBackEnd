package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.dto.client.ClientRequestDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientResponseDTO;
import co.edu.uniquindio.unieventos.dto.event.EventRequestDTO;
import co.edu.uniquindio.unieventos.dto.event.EventResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Admin;
import co.edu.uniquindio.unieventos.model.document.Client;
import co.edu.uniquindio.unieventos.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping("/create")
    public ResponseEntity<ClientResponseDTO> createClient(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO clientResponseDTO = clientService.createClient(clientRequestDTO);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @GetMapping("/findByFirstName")
    public ResponseEntity<?> getByFirstName(String firstName) {

        List<ClientResponseDTO> client = clientService.findByFirtsName(firstName);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllClients() {

        List<ClientResponseDTO> client = clientService.getAllClient();
        return ResponseEntity.ok(client);
    }

    @GetMapping("/findByLastName")
    public ResponseEntity<?> getByLastName(String lastName) {

        List<ClientResponseDTO> client = clientService.findByLastName(lastName);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> getByEmail(String email) {

        List<ClientResponseDTO> client = clientService.findByEmail(email);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/findByPhoneNumber")
    public ResponseEntity<?> getByPhoneNumber(String phoneNumber) {

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
    public ResponseEntity<Client> updateClient(@RequestParam String id, @RequestBody Client clientDetails)
    {
        clientService.updateClient(id, clientDetails);
        return ResponseEntity.ok(clientDetails);
    }

    @PutMapping("/updatePassword")
    public boolean updatePassword(@RequestParam String id, @RequestParam String newPassword)
    {
        return clientService.updatePassword(id, newPassword);
    }



}

