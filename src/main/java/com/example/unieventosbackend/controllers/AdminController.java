package com.example.unieventosbackend.controllers;


import com.example.unieventosbackend.model.documents.Admin;
import com.example.unieventosbackend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    //Crear un admin
    @PostMapping()
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin newAdmin = adminService.createAdmin(admin);
        return ResponseEntity.ok(newAdmin);
    }

    // Buscar admin por "usuario"
    @GetMapping("/usuario/{usuario}")
    public List<Admin> findByUsuario(@PathVariable String usuario) {
        return adminService.findByUsuario(usuario);
    }

    // Buscar admin por "contrasenia"
    @GetMapping("/contrasenia/{contrasenia}")
    public List<Admin> findByContrasenia(@PathVariable String contrasenia) {
        return adminService.findByContrasenia(contrasenia);
    }

    // Actualizar "usuario" por "id"
    @PutMapping("/{id}/usuario")
    public void updateUsuarioById(@PathVariable String id, @RequestBody String usuario) {
        adminService.updateUsuarioById(id, usuario);
    }

    // Actualizar "contrasenia" por "id"
    @PutMapping("/{id}/contrasenia")
    public void updateContraseniaById(@PathVariable String id, @RequestBody String contrasenia) {
        adminService.updateContraseniaById(id, contrasenia);
    }

    // Eliminar admin por "id"
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable String id) {
        adminService.deleteById(id);
    }

    // Eliminar admin por "usuario"
    @DeleteMapping("/usuario/{usuario}")
    public void deleteByUsuario(@PathVariable String usuario) {
        adminService.deleteByUsuario(usuario);
    }

    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}

