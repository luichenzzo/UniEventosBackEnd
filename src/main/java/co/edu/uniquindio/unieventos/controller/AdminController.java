package co.edu.uniquindio.unieventos.controller;


import co.edu.uniquindio.unieventos.model.document.Admin;
import co.edu.uniquindio.unieventos.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/crear")
    public ResponseEntity<Admin> crearAdmin(@RequestBody Admin admin){
        Admin newAdmin =adminService.createAdmin(admin);
        System.out.println(newAdmin.toString());
        return ResponseEntity.ok(newAdmin);
    }
}

