package com.mercadeo.servicio.usuarios.controllers;

import com.mercadeo.servicio.usuarios.dtos.auth.LoginRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.auth.LoginResponseDTO;
import com.mercadeo.servicio.usuarios.dtos.auth.RegistroUsuarioRequestDTO;
import com.mercadeo.servicio.usuarios.dtos.user.UsuarioResponseDTO;
import com.mercadeo.servicio.usuarios.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@Valid @RequestBody RegistroUsuarioRequestDTO request) {
        UsuarioResponseDTO response = userService.registrarUsuario(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> autenticarUsuario(@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = userService.autenticarUsuario(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Endpoint para solicitar restablecimiento de contraseña (solo un placeholder)
    @PostMapping("/forgot-password")
    public ResponseEntity<Void> solicitarRestablecimientoContrasena(@RequestBody String email) {
        // Lógica para enviar correo con token de restablecimiento
        System.out.println("Solicitud de restablecimiento de contraseña para: " + email);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
