package com.unir.net.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "customers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;

    @Size(min = 3, message = "Nombres debe tener mínimo 3 caracteres.")
    @Column(nullable = false, length = 70)
    private String firstName;

    @Size(min = 3, message = "Apellidos debe tener mínimo 3 caracteres.")
    @Column(nullable = false, length = 70)
    private String lastName;

    @Size(min = 1, max = 1, message = "Genero debe tener 1 caracter")
    @Column(nullable = false, length = 1)
    private String gender;

    @ManyToOne
    @JoinColumn(name = "id_type_identy_document", nullable = false,
            foreignKey = @ForeignKey(name = "FK_typeIdentityDocument_customer"))
    private TypeIdentityDocument typeIdentityDocument;

    @Column(nullable = false)
    private String numberIdentyDocument;

    @Column
    private LocalDateTime birthDate;

    @Column
    private String contactNumber;

    @Email
    @Column
    private String contacEmail;

    @Column
    private String password;

    @Column
    private boolean enabled;

}
