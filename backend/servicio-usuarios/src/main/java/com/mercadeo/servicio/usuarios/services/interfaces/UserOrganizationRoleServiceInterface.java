import com.mercadeo.servicio.usuarios.services.dtos.role.RolUsuarioOrganizacionResponseDTO;

import java.util.List;
import java.util.UUID;

public interface UserOrganizationRoleServiceInterface {
    RolUsuarioOrganizacionResponseDTO asignarRol(UUID idUsuario, UUID idOrganizacion, String rolString);
    RolUsuarioOrganizacionResponseDTO actualizarRol(UUID idUsuario, UUID idOrganizacion, String nuevoRolString, Boolean estaActivo);
    List<RolUsuarioOrganizacionResponseDTO> obtenerMiembrosDeOrganizacion(UUID idOrganizacion);
    RolUsuarioOrganizacionResponseDTO obtenerRolDeUsuarioEnOrganizacion(UUID idUsuario, UUID idOrganizacion);
}