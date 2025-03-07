package com.equiposseseaz.SistemaControlEquiposSESEAZ.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.w3c.dom.Text;

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
    @DBRef(lazy = false)
    private Equipo equipo;
    @DBRef
    private List<Personal> personal;
    private String ubicacionFisica;
    @DBRef
    private List<Licencia> licencias;
    private String nombreEquipo;
    private String contrasena;
    private String evidenciaAsignacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaAsignacion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaFinAsignacion;
    private String comentarios;
}
