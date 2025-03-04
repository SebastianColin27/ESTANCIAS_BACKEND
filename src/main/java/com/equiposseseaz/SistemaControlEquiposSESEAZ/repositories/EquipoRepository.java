package com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories;

import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Equipo;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface EquipoRepository  extends MongoRepository<Equipo, ObjectId> {
    Equipo findByNumeroSerie(String numeroSerie);

    Equipo findByMarca(String marca);
    Equipo findByModelo(String modelo);
}
