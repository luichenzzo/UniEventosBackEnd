package co.edu.uniquindio.unieventos.controller;

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
    public ResponseEntity<Client> createClient(@RequestBody Client client){
            Client newClient =clientService.createClient(client);
            System.out.println(newClient.toString());
            return ResponseEntity.ok(newClient);
    }

    @GetMapping("/findByFirstName")
    public ResponseEntity<?> getByFirstName(String firstName) {

        List<Client> client = clientService.findByFirtsName(firstName);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllClients() {

        List<Client> client = clientService.getAllClient();
        return ResponseEntity.ok(client);
    }

    @GetMapping("/findByLastName")
    public ResponseEntity<?> getByLastName(String lastName) {

        List<Client> client = clientService.findByLastName(lastName);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<?> getByEmail(String email) {

        List<Client> client = clientService.findByEmail(email);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/findByPhoneNumber")
    public ResponseEntity<?> getByPhoneNumber(String phoneNumber) {

        List<Client> client = clientService.findByPhoneNumber(phoneNumber);
        return ResponseEntity.ok(client);
    }


    @GetMapping("/findByFullName")
    public Optional<Client> getByFullName(@RequestParam String firstName, @RequestParam String lastName) {
        return clientService.findByFullName(firstName, lastName);
    }

}

