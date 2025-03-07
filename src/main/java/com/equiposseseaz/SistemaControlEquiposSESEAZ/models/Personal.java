package com.equiposseseaz.SistemaControlEquiposSESEAZ.models;
    import com.fasterxml.jackson.annotation.JsonFormat;
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
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date fechaIngreso;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private Date fechaEgreso;
    }
