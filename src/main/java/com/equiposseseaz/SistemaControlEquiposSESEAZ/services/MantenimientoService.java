package com.equiposseseaz.SistemaControlEquiposSESEAZ.services;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Asignacion;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Equipo;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Mantenimiento;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Personal;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories.AsignacionRepository;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories.EquipoRepository;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories.MantenimientoRepository;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.repositories.PersonalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import org.bson.types.ObjectId;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;

@Service
public class MantenimientoService {
    @Autowired
    private MantenimientoRepository mantenimientoRepository;

    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Autowired
    private PersonalRepository personalRepository;

    public List<Mantenimiento> obtenerTodosLosMantenimientos() {
        return mantenimientoRepository.findAll();
    }

    public Optional<Mantenimiento> obtenerMantenimientoPorId(ObjectId id) {
        return mantenimientoRepository.findById(id);
    }
    public Mantenimiento guardarMantenimiento(Mantenimiento mantenimiento) {

        // Validar Asignacion
        if (mantenimiento.getAsignacion() != null) {
            if (mantenimiento.getAsignacion().getId() == null) {
                // Si el ID de Asignacion es nulo, no se puede relacionar el mantenimiento
                // En este caso, puedes optar por:
                // 1. Lanzar una excepción, indicando que se requiere un ID de Asignacion válido
                // 2. Crear una nueva Asignacion (si tiene sentido en tu lógica de negocio)
                // 3. Simplemente no establecer la asignación (dejar mantenimiento.setAsignacion(null))
                // Aquí optaremos por lanzar una excepción para asegurar que se proporcione un ID de Asignacion existente
                throw new IllegalArgumentException("Se requiere un ID de Asignacion válido para registrar el mantenimiento.");
            } else {
                // Si el ID de Asignacion no es nulo, verificar que exista en la base de datos
                Optional<Asignacion> asignacionOptional = asignacionRepository.findById(mantenimiento.getAsignacion().getId());
                if (asignacionOptional.isEmpty()) {
                    throw new EntityNotFoundException("No se encontró la Asignacion con el ID proporcionado.");
                }
                mantenimiento.setAsignacion(asignacionOptional.get());
            }
        } else {
            // Si el objeto Asignacion es nulo, puedes optar por:
            // 1. Lanzar una excepción, indicando que se requiere una Asignacion
            // 2. Permitir mantenimientos sin Asignacion (si tiene sentido)
            // Aquí optaremos por lanzar una excepción para asegurar que se proporcione una Asignacion
            throw new IllegalArgumentException("Se requiere una Asignacion para registrar el mantenimiento.");
        }

        return mantenimientoRepository.save(mantenimiento);
    }

  /*  public Mantenimiento guardarMantenimiento(Mantenimiento mantenimiento) {

        // Validar Equipo
        if (mantenimiento.getEquipo() != null) {
            if (mantenimiento.getEquipo().getId() == null) {
                //Si el ID es nulo, es un equipo nuevo que hay que persistir
                mantenimiento.setEquipo(equipoRepository.save(mantenimiento.getEquipo()));
            } else {
                //Si el ID no es nulo, hay que verificar que exista en la base de datos
                Equipo equipo = equipoRepository.findById(mantenimiento.getEquipo().getId())
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró el equipo con el ID proporcionado."));
                mantenimiento.setEquipo(equipo);
            }
        }

        // Validar Personal
        if (mantenimiento.getAsignadoA() != null) {
            if (mantenimiento.getAsignadoA().getId() == null) {
                //Si el ID es nulo, es un personal nuevo que hay que persistir
                mantenimiento.setAsignadoA(personalRepository.save(mantenimiento.getAsignadoA()));
            } else {
                //Si el ID no es nulo, hay que verificar que exista en la base de datos
                Personal personal = personalRepository.findById(mantenimiento.getAsignadoA().getId())
                        .orElseThrow(() -> new EntityNotFoundException("No se encontró el personal con el ID proporcionado."));
                mantenimiento.setAsignadoA(personal);
            }
        }

        return mantenimientoRepository.save(mantenimiento);
    }*/



    public boolean existeMantenimientoPorId(ObjectId id) {
        return mantenimientoRepository.existsById(id);
    }

    public void eliminarMantenimiento(ObjectId id) {
        mantenimientoRepository.deleteById(id);
    }




    public List<Mantenimiento> obtenerMantenimientosPorAsignacion(ObjectId asignacionId) {
        return mantenimientoRepository.findByAsignacionId(asignacionId);
    }

    public List<Mantenimiento> obtenerMantenimientosPorEquipo(ObjectId equipoId) {
        List<Mantenimiento> mantenimientosResult = new ArrayList<>();

        Optional<Equipo> equipoOptional = equipoRepository.findById(equipoId);
        if (equipoOptional.isPresent()) {
            List<Asignacion> asignaciones = asignacionRepository.findByEquipoId(equipoId);

            if (!asignaciones.isEmpty()) {
                for (Asignacion asignacion : asignaciones) {
                    List<Mantenimiento> mantenimientosForAsignacion = mantenimientoRepository.findByAsignacionId(asignacion.getId());
                    mantenimientosResult.addAll(mantenimientosForAsignacion);
                }
            }
        }

        return mantenimientosResult;
    }


}
