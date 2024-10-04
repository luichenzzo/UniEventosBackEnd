package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.model.document.Admin;
import co.edu.uniquindio.unieventos.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // Buscar por "usuario"
    public List<Admin> findByUsuario(String usuario) {
        return adminRepository.findByUsuario(usuario);
    }

    // Buscar por "contrasenia"
    public List<Admin> findByContrasenia(String contrasenia) {
        return adminRepository.findByContrasenia(contrasenia);
    }

    // Actualizar "usuario" por "id"
    public void updateUsuarioById(String id, String usuario) {
        adminRepository.updateUsuarioById(id, usuario);
    }

    // Actualizar "contrasenia" por "id"
    public void updateContraseniaById(String id, String contrasenia) {
        adminRepository.updateContraseniaById(id, contrasenia);
    }

    // Eliminar por "id"
    public void deleteById(String id) {
        adminRepository.deleteById(id);
    }

    // Eliminar por "usuario"
    public void deleteByUsuario(String usuario) {
        adminRepository.deleteByUsuario(usuario);
    }

    //Agregar un Admin
    public Admin createAdmin(Admin admin){
        return adminRepository.save(admin);
    }


    public Optional<Admin> findByUsuarioAndContrasenia(String usuario, String contrasenia) {
        return adminRepository.findByUsuarioAndContrasenia(usuario, contrasenia);
    }
}
