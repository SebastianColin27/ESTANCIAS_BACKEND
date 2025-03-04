package com.equiposseseaz.SistemaControlEquiposSESEAZ.services;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Equipo;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories.EquipoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;

@Service
public class EquipoService {
    @Autowired
    private EquipoRepository equipoRepository;


    public List<Equipo> obtenerTodosLosEquipos() {
        return equipoRepository.findAll();
    }


    public Optional<Equipo> obtenerEquipoPorId(ObjectId id) {
        return equipoRepository.findById(id);
    }



    public Equipo crearEquipo(Equipo equipo) {


        return equipoRepository.save(equipo);


    }


    public Equipo actualizarEquipo(Equipo equipo) {


        return equipoRepository.save(equipo);


    }

    public void eliminarEquipo(ObjectId id) {
        equipoRepository.deleteById(id);
    }


    public boolean existeEquipoPorId(ObjectId id) {
        return equipoRepository.existsById(id);
    }


    public Equipo buscarEquipoPorNumeroDeSerie(String numeroSerie) {
        return equipoRepository.findByNumeroSerie(numeroSerie);
    }



    public Equipo buscarEquipoPorMarca(String marca) {
        return equipoRepository.findByMarca(marca);
    }

    public Equipo buscarEquipoPorModelo(String modelo) {
        return equipoRepository.findByModelo(modelo);
    }



}
