package cl.org.inmobiliaria.monte.zion.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles") // Nombre de la tabla para los roles
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "nombre_rol", unique = true, nullable = false, length = 50)
    private String nombreRol; // Ej. "ADMINISTRADOR", "AGENTE_VENTAS"

    @Column(name = "descripcion", length = 200)
    private String descripcion; // Descripción del rol

    // Si quieres que el rol sepa qué usuarios lo tienen (opcional)
    @ManyToMany(mappedBy = "roles")
    private Set<User> usuarios = new HashSet<>();

    // Constructor sin id para facilitar la creación de nuevos roles
    public Rol(String nombreRol, String descripcion) {
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
    }
}
