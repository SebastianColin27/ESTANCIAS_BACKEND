package com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories;

import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Mantenimiento;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MantenimientoRepository extends MongoRepository<Mantenimiento, ObjectId> {
    List<Mantenimiento> findByAsignacionId(ObjectId asignacionId);

    List<Mantenimiento> findByAsignacion_Equipo_Id(ObjectId equipoId);
}
