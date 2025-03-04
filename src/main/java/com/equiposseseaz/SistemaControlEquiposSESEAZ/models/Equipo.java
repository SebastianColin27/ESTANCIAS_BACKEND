package com.equiposseseaz.SistemaControlEquiposSESEAZ.models;

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
@Document("equipos")

public class Equipo {
    @Id
    private ObjectId id;
    private String numeroSerie;
    private String tipo;
    private String marca;
    private String color;
    private String modelo;
    private String procesador;
    private int ram;
    private double almacenamiento;
    private String puertos;
    private String estado;
    private Date fechaCompra;
    private String imagen;
}
