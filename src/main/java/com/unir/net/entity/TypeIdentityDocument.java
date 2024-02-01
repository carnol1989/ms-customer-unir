package com.unir.net.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "types_identity_documents")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TypeIdentityDocument implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypeIdentityDocument;

    @Column
    private String typeIdentyDocument;

    @Column
    private boolean enabled;

}
