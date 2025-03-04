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
    }

    public boolean existeMantenimientoPorId(ObjectId id) {
        return mantenimientoRepository.existsById(id);
    }

    public void eliminarMantenimiento(ObjectId id) {
        mantenimientoRepository.deleteById(id);
    }

    public List<Mantenimiento> obtenerMantenimientosPorEquipo(ObjectId equipoId) {
        return mantenimientoRepository.findByEquipoId(equipoId);
    }
}
