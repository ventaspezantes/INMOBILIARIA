package cl.org.inmobiliaria.monte.zion.backend.entities;

import cl.org.inmobiliaria.monte.zion.backend.utils.EstadoCuenta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data // Genera getters, setters, toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin argumentos
@AllArgsConstructor // Genera un constructor con todos los argumentos
@Entity // Indica que esta clase es una entidad JPA
@Table(name = "usuarios") // Nombre de la tabla en la base de datos


public class User {

        @Id // Marca este campo como la clave primaria
        @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
        @Column(name = "id_usuario")
        private Long idUsuario;

        @Column(name = "nombre_usuario", unique = true, nullable = false, length = 50)
        private String nombreUsuario; // Login

        @Column(name = "contrasena_hash", nullable = false, length = 255)
        private String contrasenaHash; // Contraseña encriptada (hash)

        @Column(name = "nombre_completo", nullable = false, length = 100)
        private String nombreCompleto;

        @Column(name = "correo_electronico", nullable = false, unique = true, length = 100)
        private String correoElectronico;

        @Column(name = "telefono_contacto", length = 20)
        private String telefonoContacto; // Opcional

    @ManyToMany(fetch = FetchType.EAGER) // Carga los roles cuando se carga el usuario
    @JoinTable( // Configuración de la tabla intermedia
            name = "usuario_roles", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "id_usuario"), // Columna de esta entidad (Usuario) en la tabla intermedia
            inverseJoinColumns = @JoinColumn(name = "id_rol") // Columna de la entidad relacionada (Rol) en la tabla intermedia
    )
    private Set<Rol> roles = new HashSet<>();

        @Column(name = "fecha_creacion", nullable = false)
        private LocalDateTime fechaCreacion = LocalDateTime.now(); // Fecha de Creación de la Cuenta

        @Column(name = "ultimo_inicio_sesion")
        private LocalDateTime ultimoInicioSesion;

        @Enumerated(EnumType.STRING) // Almacena el enum como String en la base de datos
        @Column(name = "estado_cuenta", nullable = false, length = 20)
        private EstadoCuenta estadoCuenta = EstadoCuenta.ACTIVO; // Estado de la Cuenta

        @Column(name = "fecha_vencimiento_contrasena")
        private LocalDate fechaVencimientoContrasena; // Opcional

        @Column(name = "notas_internas", columnDefinition = "TEXT")
        private String notasInternas; // Opcional
}
