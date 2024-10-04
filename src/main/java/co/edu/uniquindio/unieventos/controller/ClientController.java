package co.edu.uniquindio.unieventos.controller;

import co.edu.uniquindio.unieventos.model.document.Client;
import co.edu.uniquindio.unieventos.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/clients")
public class ClientController {

        @Autowired
        private ClientService clientService;


    @GetMapping("/findByFirtsName")
    public ResponseEntity<?> getAllProducts(String firstName) {

            List<Client> client = clientService.findByFirtsName(firstName);
            return ResponseEntity.ok(client);

    }

        @PostMapping("/create")
        public ResponseEntity<Client> createClient(@RequestBody Client client){
            Client newClient =clientService.createClient(client);
            System.out.println(newClient.toString());
            return ResponseEntity.ok(newClient);
        }
}

