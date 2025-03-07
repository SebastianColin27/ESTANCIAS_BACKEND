package com.equiposseseaz.SistemaControlEquiposSESEAZ.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

@Document(value = "licencias")
public class Licencia {
    @Id
    private ObjectId id;
    private String tipoSw;
    private String nombreLicencia;
    private String numeroSerie;
    private int numeroUsuarios;
    private String suscripcion;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaVencimiento;
    private String usuario;
    private String contrasena;
}
