package com.equiposseseaz.SistemaControlEquiposSESEAZ.models;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document("asignaciones")

public class Asignacion {
    @Id
    private ObjectId id;
    @DBRef
    private Equipo equipo;
    @DBRef
    private List<Personal> personal; // <----  MUST BE List<Personal>
    private String ubicacionFisica;
    @DBRef
    private List<Licencia> licencias;
    private String nombreEquipo;
    private String contrasena;
    private String evidenciaAsignacion;
    private Date fechaAsignacion;
    private Date fechaFinAsignacion;
}
