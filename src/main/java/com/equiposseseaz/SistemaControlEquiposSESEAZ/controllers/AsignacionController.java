package com.equiposseseaz.SistemaControlEquiposSESEAZ.controllers;

import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Asignacion;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.services.AsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/asignaciones")
public class AsignacionController {
    @Autowired
    private AsignacionService asignacionService;


    @GetMapping
    public List<Asignacion> obtenerTodasLasAsignaciones() {
        return asignacionService.obtenerTodasLasAsignaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignacion> obtenerAsignacionPorId(@PathVariable ObjectId id) {
        Optional<Asignacion> asignacion = asignacionService.obtenerAsignacionPorId(id);
        return asignacion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Asignacion> crearAsignacion(@Valid @RequestBody Asignacion asignacion) {
        Asignacion nuevaAsignacion = asignacionService.guardarAsignacion(asignacion);
        return new ResponseEntity<>(nuevaAsignacion, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignacion> actualizarAsignacion(
            @PathVariable ObjectId id,
            @Valid @RequestBody Asignacion asignacion) {
//Se crea una nueva asignacion si la anterior ya está cerrada
        if(!asignacionService.existeAsignacionConId(id) ){
            return ResponseEntity.notFound().build();


        }


        return new ResponseEntity<>(asignacionService.guardarAsignacion(asignacion), HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarAsignacion(@PathVariable ObjectId id) {
        if (!asignacionService.existeAsignacionConId(id)) {
            return ResponseEntity.notFound().build();
        }
        asignacionService.eliminarAsignacionPorId(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/persona/{personalId}")
    public ResponseEntity<List<Asignacion>> obtenerAsignacionesPorPersona(@PathVariable ObjectId personalId) {
        List<Asignacion> asignaciones = asignacionService.obtenerAsignacionesPorPersona(personalId);
        return ResponseEntity.ok(asignaciones);


    }


    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<Asignacion>> obtenerAsignacionesPorEquipo(@PathVariable ObjectId equipoId) {
        List<Asignacion> asignaciones = asignacionService.obtenerAsignacionesPorEquipo(equipoId);
        return ResponseEntity.ok(asignaciones);


    }
}
