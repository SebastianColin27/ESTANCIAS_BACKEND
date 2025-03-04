package com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Licencia;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface LicenciaRepository extends MongoRepository<Licencia, ObjectId> {
    List<Licencia> findByNombreLicenciaContainingIgnoreCase(String nombreLicencia);
}
