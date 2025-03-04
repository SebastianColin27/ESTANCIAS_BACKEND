package com.equiposseseaz.SistemaControlEquiposSESEAZ.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Document("usuarios")

public class Usuario {

    @Id
    private ObjectId id;
    private String nombreUsuario;
    private String contrasena;


    private List<String> roles;
}
