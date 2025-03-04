package com.equiposseseaz.SistemaControlEquiposSESEAZ.models;
    import lombok.*;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;
    import java.util.Date;
    import org.bson.types.ObjectId;
    import java.util.List; // <---- IMPORT LIST!

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Document("personal") // Nombre de la colección en MongoDB
    public class Personal {
        @Id
        private ObjectId id;
        private String nombre;
        private String cargo;
        private Date fechaIngreso;
        private Date fechaEgreso;
    }
