package com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories;

import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Personal;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface PersonalRepository extends MongoRepository<Personal, ObjectId> {
    //Para busquedas por nombre (o parte del nombre)
    List<Personal> findByNombreContainingIgnoreCase(String nombre);


}
