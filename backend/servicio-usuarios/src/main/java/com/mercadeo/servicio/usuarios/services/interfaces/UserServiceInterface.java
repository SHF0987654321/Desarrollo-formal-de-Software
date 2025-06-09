package com.mercadeo.servicio.usuarios.services.interfaces;

import com.mercadeo.servicio.usuarios.services.dtos.auth.LoginRequestDTO;
import com.mercadeo.servicio.usuarios.services.dtos.auth.LoginResponseDTO;
import com.mercadeo.servicio.usuarios.services.dtos.auth.RegistroUsuarioRequestDTO;
import com.mercadeo.servicio.usuarios.services.dtos.user.ActualizarUsuarioRequestDTO;
import com.mercadeo.servicio.usuarios.services.dtos.user.PasswordUpdateRequestDTO;
import com.mercadeo.servicio.usuarios.services.dtos.user.UsuarioResponseDTO;

import java.util.UUID;

public interface UserServiceInterface {
    UsuarioResponseDTO registrarUsuario(RegistroUsuarioRequestDTO request);
    LoginResponseDTO autenticarUsuario(LoginRequestDTO request);
    UsuarioResponseDTO obtenerUsuarioPorId(UUID id);
    UsuarioResponseDTO obtenerUsuarioPorCorreo(String correo);
    UsuarioResponseDTO actualizarContrasena(UUID idUsuario, PasswordUpdateRequestDTO request);
    UsuarioResponseDTO actualizarPerfilUsuario(UUID idUsuario, ActualizarUsuarioRequestDTO request);
}