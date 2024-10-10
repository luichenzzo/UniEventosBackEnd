package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.Excepciones.NoExistenciaUsuarioException;
import co.edu.uniquindio.unieventos.dto.admin.AdminRequestDTO;
import co.edu.uniquindio.unieventos.dto.admin.AdminResponseDTO;
import co.edu.uniquindio.unieventos.dto.event.EventRequestDTO;
import co.edu.uniquindio.unieventos.dto.event.EventResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Admin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import co.edu.uniquindio.unieventos.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    @Autowired
    private AdminRepository adminRepository;

    // Método para convertir de DTO a entidad
    private Admin convertToEntity(AdminRequestDTO dto) {
        return Admin.builder()
                .usuario(dto.usuario())
                .contrasenia(dto.contrasenia())
                .build();
    }

    // Método para convertir de entidad a DTO
    private AdminResponseDTO convertToDTO(Admin admin) {
        return new AdminResponseDTO(admin.getUsuario());
    }

    // Crear un nuevo admin
    public AdminResponseDTO createAdmin(AdminRequestDTO adminRequestDTO) {
        Admin admin = convertToEntity(adminRequestDTO);
        Admin savedAdmin = adminRepository.save(admin);
        return convertToDTO(savedAdmin);
    }

    // Buscar admins por usuario
    public List<AdminResponseDTO> findByUsuario(String usuario) {
        return adminRepository.findByUsuarioContainingIgnoreCase(usuario)
                .stream()
                .map(this::convertToDTO)  // Transformar de entidad a DTO
                .collect(Collectors.toList());
    }

    // Eliminar por "id"
    public boolean deleteById(String usuario) throws NoExistenciaUsuarioException {
        if(adminRepository.existsById(usuario)){
            adminRepository.deleteById(usuario);
            return true;
        }
        else{
            return false;
        }
    }

    // Eliminar por "usuario"
    public void deleteByUsuario(String usuario) {
        adminRepository.deleteByUsuario(usuario);
    }


    public String iniciarSesion(String usuario, String contrasenia) {
        Admin admin = adminRepository.findByUsuarioAndContrasenia(usuario, contrasenia);
        if (admin != null) {
            return generarToken(admin);
        } else {
            throw new RuntimeException("Credenciales inválidas");
        }
    }

    private String generarToken(Admin admin) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", admin.getId());
        claims.put("usuario", admin.getUsuario());

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(admin.getUsuario())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }


}


