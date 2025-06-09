package com.mercadeo.servicio.usuarios.services;

import com.mercadeo.servicio.usuarios.dtos.auth.LoginRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.auth.LoginResponseDTO;
import com.mercadeo.servicio.usuarios.dtos.auth.RegistroUsuarioRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.user.ActualizarUsuarioRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.user.PasswordUpdateRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.user.UsuarioResponseDTO;
import com.mercadeo.servicio.usuarios.exception.BadRequestException;
import com.mercadeo.servicio.usuarios.exception.ResourceNotFoundException;
import com.mercadeo.servicio.usuarios.models.Usuario;
import com.mercadeo.servicio.usuarios.repository.UsuarioRepository;
import com.mercadeo.servicio.usuarios.services.interfaces.UserServiceInterface;
import com.mercadeo.servicio.usuarios.util.JwtUtil; // Asumiendo que tienes una clase JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil; // Para la generación de tokens

    public UserServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    @Transactional
    public UsuarioResponseDTO registrarUsuario(RegistroUsuarioRequestDTO request) {
        if (usuarioRepository.findByCorreoElectronico(request.getCorreoElectronico()).isPresent()) {
            throw new BadRequestException("El correo electrónico ya está registrado.");
        }

        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico(request.getCorreoElectronico());
        usuario.setContrasenaHash(passwordEncoder.encode(request.getContrasena()));
        usuario.setNombre(request.getNombre());

        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return mapToUsuarioResponseDTO(nuevoUsuario);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDTO autenticarUsuario(LoginRequestDTO request) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(request.getCorreoElectronico())
                .orElseThrow(() -> new BadRequestException("Credenciales inválidas."));

        if (!passwordEncoder.matches(request.getContrasena(), usuario.getContrasenaHash())) {
            throw new BadRequestException("Credenciales inválidas.");
        }

        String token = jwtUtil.generateToken(usuario.getId().toString(), usuario.getCorreoElectronico(), usuario.getNombre());

        LoginResponseDTO response = new LoginResponseDTO();
        response.setAccessToken(token);
        response.setIdUsuario(usuario.getId());
        response.setCorreoElectronico(usuario.getCorreoElectronico());
        response.setNombre(usuario.getNombre());
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerUsuarioPorId(UUID id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", id));
        return mapToUsuarioResponseDTO(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerUsuarioPorCorreo(String correo) {
        Usuario usuario = usuarioRepository.findByCorreoElectronico(correo)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "correo", correo));
        return mapToUsuarioResponseDTO(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizarContrasena(UUID idUsuario, PasswordUpdateRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));

        if (!passwordEncoder.matches(request.getContrasenaActual(), usuario.getContrasenaHash())) {
            throw new BadRequestException("La contraseña actual es incorrecta.");
        }

        usuario.setContrasenaHash(passwordEncoder.encode(request.getNuevaContrasena()));
        Usuario updatedUser = usuarioRepository.save(usuario);
        return mapToUsuarioResponseDTO(updatedUser);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO actualizarPerfilUsuario(UUID idUsuario, ActualizarUsuarioRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario", "id", idUsuario));

        Optional.ofNullable(request.getNombre()).ifPresent(usuario::setNombre);

        Usuario updatedUser = usuarioRepository.save(usuario);
        return mapToUsuarioResponseDTO(updatedUser);
    }

    private UsuarioResponseDTO mapToUsuarioResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setCorreoElectronico(usuario.getCorreoElectronico());
        dto.setNombre(usuario.getNombre());
        dto.setFechaCreacion(usuario.getFechaCreacion());
        return dto;
    }
}