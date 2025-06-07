package gtics.lab7_20223209.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "proveedor")
@Getter
@Setter
@JsonIgnoreProperties({"sitioWeb", "direccionFisica", "facturacionAnualDolares", "fechaRegistro", "ultimaActualizacion"})
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idProveedor", nullable = false)
    private int idProveedor;

    @Size(max = 100, message = "La razón social no debe superar los 100 caracteres")
    @NotBlank(message = "La razón social es obligatoria")
    @Column(name = "razonSocial")
    private String razonSocial;

    @Size(max = 100, message = "El nombre comercial no debe superar los 100 caracteres")
    @NotBlank(message = "El nombre comercial es obligatorio")
    @Column(name = "nombreComercial")
    private String nombreComercial;

    @Digits(integer = 11, fraction = 0, message = "El RUC debe tener como máximo 11 dígitos")
    @NotNull(message = "El RUC es obligatorio")
    @Column(name = "ruc")
    private Long ruc;

    @NotNull(message = "El teléfono es obligatorio")
    @Column(name = "telefono")
    private Integer telefono;

    @Email(message = "Debe ingresar un correo válido")
    @Size(max = 100, message = "El correo no debe superar los 100 caracteres")
    @NotBlank(message = "El correo es obligatorio")
    @Column(name = "correo")
    private String correo;

    @Size(max = 100, message = "El sitio web no debe superar los 100 caracteres")
    @Column(name = "sitioWeb")
    private String sitioWeb;

    @Size(max = 150, message = "La dirección física no debe superar los 150 caracteres")
    @Column(name = "direccionFisica")
    private String direccionFisica;

    @Size(max = 20, message = "El país no debe superar los 20 caracteres")
    @Column(name = "pais")
    private String pais;

    @Size(max = 80, message = "El representante legal no debe superar los 80 caracteres")
    @Column(name = "representanteLegal")
    private String representanteLegal;

    @Column(name = "dniRepresentante")
    private Long dniRepresentante;

    @Size(max = 20, message = "El tipo de proveedor no debe superar los 20 caracteres")
    @Column(name = "tipoProveedor")
    private String tipoProveedor;

    @Size(max = 45, message = "La categoría no debe superar los 45 caracteres")
    @Column(name = "categoria")
    private String categoria;

    @Column(name = "facturacionAnualDolares")
    private Double facturacionAnualDolares;

    @Column(name = "fechaRegistro")
    private LocalDateTime fechaRegistro;

    @Column(name = "ultimaActualizacion")
    private LocalDateTime ultimaActualizacion;

    @PrePersist
    public void prePersist() {
        fechaRegistro = LocalDateTime.now();
        ultimaActualizacion = null; // Se establece en null al crear el registro
    }

    @PreUpdate
    public void preUpdate() {
        ultimaActualizacion = LocalDateTime.now();
    }

    @Column(name = "estado")
    private Boolean estado;
}
