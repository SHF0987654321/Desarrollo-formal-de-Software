pakage  com.mercadeo.servicio.usuarios.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.Set;

@Entity
@Table(name = "usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "correo_electronico", unique = true, nullable = false)
    private String correoElectronico;

    @Column(name = "contrasena_hash", nullable = false)
    private String contrasenaHash;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @CreationTimestamp
    @Column(name = "fecha_creacion", nullable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @UpdateTimestamp
    @Column(name = "fecha_actualizacion", nullable = false)
    private LocalDateTime fechaActualizacion;

    // Relaciones (bidireccionales si es necesario, aquí solo las que se usan)
    @OneToMany(mappedBy = "usuarioPropietario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Organizacion> organizacionesPropias;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<RolUsuarioOrganizacion> rolesOrganizacion;

    @OneToMany(mappedBy = "usuarioInvitador", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<InvitacionOrganizacion> invitacionesEnviadas;
}