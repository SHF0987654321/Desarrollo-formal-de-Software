package com.mercadeo.servicio.usuarios.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.UUID;

// NOTA: Esta es una implementación MUY simplificada de JWT para fines de demostración.
// En un entorno de producción, se necesitaría una configuración de seguridad más robusta
// con Spring Security, manejo de claves secretas seguras, expiración de tokens, etc.
@Component
public class JwtUtil {

    // Genera una clave segura para HmacSha256
    // EN UN ENTORNO REAL, ESTA CLAVE DEBERÍA SER LEÍDA DE VARIABLES DE ENTORNO O UN SERVICIO DE CONFIGURACIÓN SEGURO.
    // NO DEBE ESTAR HARCODEADA AQUÍ.
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 864_000_00; // 1 día en milisegundos (24 * 60 * 60 * 1000)

    /**
     * Genera un nuevo token JWT.
     *
     * @param userId El ID único del usuario (será el "subject" del token).
     * @param email  El correo electrónico del usuario (será una "claim").
     * @param username El nombre de usuario del usuario (será una "claim").
     * @return El token JWT generado.
     */
    public String generateToken(String userId, String email, String username) {
        return Jwts.builder()
                .setSubject(userId) // El "subject" del token es típicamente el ID del usuario.
                .claim("email", email) // Añade el correo electrónico como una claim personalizada.
                .claim("username", username) // Añade el nombre de usuario como una claim personalizada.
                .setIssuedAt(new Date()) // Fecha de emisión del token.
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Fecha de expiración.
                .signWith(secretKey) // Firma el token con la clave secreta.
                .compact(); // Construye y compacta el token.
    }

    /**
     * Extrae todas las "claims" (declaraciones) de un token JWT.
     *
     * @param token El token JWT del que extraer las claims.
     * @return Un objeto Claims que contiene todas las declaraciones del token.
     * @throws ExpiredJwtException Si el token ha expirado.
     * @throws SignatureException Si la firma del token es inválida.
     * @throws IllegalArgumentException Si el token es inválido o mal formado.
     */
    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey) // Establece la clave de firma para la validación.
                    .build()
                    .parseClaimsJws(token) // Parsea y valida el token.
                    .getBody(); // Obtiene el cuerpo del token (las claims).
        } catch (ExpiredJwtException e) {
            System.err.println("Token JWT expirado: " + e.getMessage());
            throw new ExpiredJwtException(e.getHeader(), e.getClaims(), "El token JWT ha expirado.");
        } catch (SignatureException e) {
            System.err.println("Firma JWT inválida: " + e.getMessage());
            throw new SignatureException("Firma JWT inválida.");
        } catch (Exception e) {
            System.err.println("Error al parsear token JWT: " + e.getMessage());
            throw new IllegalArgumentException("Token JWT inválido o mal formado: " + e.getMessage());
        }
    }

    /**
     * Extrae una "claim" específica de un token JWT.
     *
     * @param token El token JWT.
     * @param claimsResolver Una función para resolver la claim deseada desde el objeto Claims.
     * @param <T> El tipo de la claim a extraer.
     * @return La claim extraída.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrae el nombre de usuario (la claim "username") del token.
     *
     * @param token El token JWT.
     * @return El nombre de usuario.
     */
    public String extractUsername(String token) {
        return extractClaim(token, claims -> claims.get("username", String.class));
    }

    /**
     * Extrae el correo electrónico (la claim "email") del token.
     *
     * @param token El token JWT.
     * @return El correo electrónico.
     */
    public String extractEmail(String token) {
        return extractClaim(token, claims -> claims.get("email", String.class));
    }

    /**
     * Extrae el ID del usuario (el "subject" del token).
     *
     * @param token El token JWT.
     * @return El ID del usuario.
     */
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae la fecha de expiración del token.
     *
     * @param token El token JWT.
     * @return La fecha de expiración.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Verifica si un token JWT ha expirado.
     *
     * @param token El token JWT.
     * @return true si el token ha expirado, false en caso contrario.
     */
    public Boolean isTokenExpired(String token) {
        // Maneja la ExpiredJwtException directamente en extractAllClaims.
        // Si llegamos aquí y extractExpiration no lanzó una excepción, significa que el token no ha expirado aún.
        try {
            return extractExpiration(token).before(new Date());
        } catch (ExpiredJwtException e) {
            return true; // El token ya ha expirado.
        }
    }

    /**
     * Valida un token JWT contra los detalles de un usuario.
     *
     * @param token El token JWT a validar.
     * @param userDetails Los detalles del usuario (obtenidos de un UserDetailsService).
     * @return true si el token es válido para el usuario, false en caso contrario.
     */
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userIdInToken = extractUserId(token); // Obtenemos el userId del token
        // Compara el ID del usuario del token con el "username" de UserDetails (que en este caso debería ser el ID del usuario)
        // y verifica que el token no haya expirado.
        return (userIdInToken.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}