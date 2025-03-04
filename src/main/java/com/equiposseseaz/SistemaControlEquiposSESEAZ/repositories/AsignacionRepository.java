package com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories;

import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Asignacion;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AsignacionRepository extends MongoRepository<Asignacion, ObjectId> {
    //Obtener el historial de personal y equipo
    List<Asignacion> findByPersonalId(ObjectId personalId);

    List<Asignacion> findByEquipoId(ObjectId equipoId);

    Optional<Asignacion> findByEquipoIdAndFechaAsignacionLessThanEqualAndFechaFinAsignacionGreaterThanEqual(ObjectId equipoId, Date fechaAsignacion, Date fechaFinAsignacion);
}
