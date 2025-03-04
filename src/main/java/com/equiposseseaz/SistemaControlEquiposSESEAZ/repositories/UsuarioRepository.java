package com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories;

import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Usuario;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, ObjectId> {
    Optional<Usuario> findByNombreUsuario(String nombreUsuario);
    List<Usuario> findByNombreUsuarioContainingIgnoreCase(String nombreUsuario);
    boolean existsById(ObjectId id);
}
