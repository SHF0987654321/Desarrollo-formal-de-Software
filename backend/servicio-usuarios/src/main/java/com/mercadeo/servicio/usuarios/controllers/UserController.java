package com.mercadeo.servicio.usuarios.controllers;

import com.mercadeo.servicio.usuarios.dtos.user.ActualizarUsuarioRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.user.PasswordUpdateRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.user.UsuarioResponseDTO;
import com.mercadeo.servicio.usuarios.services.interfaces.UserServiceInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuarios")
@Validated
public class UsuarioController {

    private final UserServiceInterface userService;

    public UsuarioController(UserServiceInterface userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerUsuarioPorId(@PathVariable UUID id) {
        // En un sistema real, aquí se verificaría que el usuario autenticado
        // sea el mismo que el ID solicitado o tenga rol de administrador.
        UsuarioResponseDTO response = userService.obtenerUsuarioPorId(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> actualizarContrasena(@PathVariable UUID id, @Valid @RequestBody PasswordUpdateRequestDTO request) {
        // En un sistema real, se verificaría la autenticación y autorización.
        userService.actualizarContrasena(id, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizarPerfilUsuario(@PathVariable UUID id, @Valid @RequestBody ActualizarUsuarioRequestDTO request) {
        // En un sistema real, se verificaría la autenticación y autorización.
        UsuarioResponseDTO response = userService.actualizarPerfilUsuario(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}