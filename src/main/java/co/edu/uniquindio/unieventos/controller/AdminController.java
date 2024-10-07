package co.edu.uniquindio.unieventos.controller;


import co.edu.uniquindio.unieventos.Excepciones.NoExistenciaUsuarioException;
import co.edu.uniquindio.unieventos.dto.admin.AdminRequestDTO;
import co.edu.uniquindio.unieventos.dto.admin.AdminResponseDTO;
import co.edu.uniquindio.unieventos.dto.client.ClientResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Admin;
import co.edu.uniquindio.unieventos.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Crear un nuevo admin
    @PostMapping("/create")
    public ResponseEntity<AdminResponseDTO> createAdmin(@RequestBody AdminRequestDTO adminRequestDTO) {
        AdminResponseDTO createdAdmin = adminService.createAdmin(adminRequestDTO);
        return ResponseEntity.ok(createdAdmin);
    }

    // Buscar admins por usuario
    @GetMapping("/findByUsuario")
    public ResponseEntity<?> findByUsuario(@RequestParam String usuario ) {

        List<AdminResponseDTO> client = adminService.findByUsuario(usuario);
        return ResponseEntity.ok(client);
    }


    // Eliminar admin por ID
    @DeleteMapping("/deleteByUsuario")
    public boolean deleteByUsuario(@RequestParam String usuario) throws NoExistenciaUsuarioException {

        return adminService.deleteById(usuario);
    }

    // Buscar admin por usuario y contrasenia
    @PostMapping("/login")
    public ResponseEntity<AdminResponseDTO> login(@RequestBody AdminRequestDTO adminRequestDTO) {
        Optional<AdminResponseDTO> admin = adminService.findByUsuarioAndContrasenia(adminRequestDTO.usuario(), adminRequestDTO.contrasenia());
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}


