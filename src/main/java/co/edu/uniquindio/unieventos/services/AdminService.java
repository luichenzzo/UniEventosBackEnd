package co.edu.uniquindio.unieventos.services;

import co.edu.uniquindio.unieventos.Excepciones.NoExistenciaUsuarioException;
import co.edu.uniquindio.unieventos.dto.admin.AdminRequestDTO;
import co.edu.uniquindio.unieventos.dto.admin.AdminResponseDTO;
import co.edu.uniquindio.unieventos.dto.event.EventRequestDTO;
import co.edu.uniquindio.unieventos.dto.event.EventResponseDTO;
import co.edu.uniquindio.unieventos.dto.location.LocationResponseDTO;
import co.edu.uniquindio.unieventos.model.document.Admin;
import co.edu.uniquindio.unieventos.model.document.Event;
import co.edu.uniquindio.unieventos.model.vo.Location;
import co.edu.uniquindio.unieventos.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

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

    // Buscar admin por usuario y contrasenia
    public Optional<AdminResponseDTO> findByUsuarioAndContrasenia(String usuario, String contrasenia) {
        return adminRepository.findByUsuarioAndContrasenia(usuario, contrasenia)
                .map(this::convertToDTO);  // Transformar de entidad a DTO
    }


}


