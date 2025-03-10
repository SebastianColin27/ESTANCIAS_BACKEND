package com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories;

import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Usuarios;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuarios, ObjectId> {
    Optional<Usuarios> findByNombreUsuario(String nombreUsuario);
    List<Usuarios> findByNombreUsuarioContainingIgnoreCase(String nombreUsuario);
    boolean existsById(ObjectId id);
}
