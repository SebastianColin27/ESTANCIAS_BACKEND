package com.equiposseseaz.SistemaControlEquiposSESEAZ.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("mantenimientos")

public class Mantenimiento {
    @Id
    private ObjectId id;
    private Date fecha;
    private String actividadRealizada;
    private String evidencia;
    @DBRef
    private Equipo equipo;
    @DBRef(lazy = false)
    private Personal asignadoA;
}
