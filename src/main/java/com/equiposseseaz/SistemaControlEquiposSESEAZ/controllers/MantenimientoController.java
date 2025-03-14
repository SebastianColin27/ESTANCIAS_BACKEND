package com.equiposseseaz.SistemaControlEquiposSESEAZ.controllers;

import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Mantenimiento;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.services.MantenimientoService;
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
@RequestMapping("/api/mantenimientos")
public class MantenimientoController {
    @Autowired
    private MantenimientoService mantenimientoService;

    @GetMapping
    public ResponseEntity<List<Mantenimiento>> obtenerTodosLosMantenimientos() {
        return ResponseEntity.ok(mantenimientoService.obtenerTodosLosMantenimientos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mantenimiento> obtenerMantenimientoPorId(@PathVariable ObjectId id) {
        Optional<Mantenimiento> mantenimiento = mantenimientoService.obtenerMantenimientoPorId(id);
        return mantenimiento.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Mantenimiento> crearMantenimiento(@Valid @RequestBody Mantenimiento mantenimiento) {
        try {
            Mantenimiento nuevoMantenimiento = mantenimientoService.guardarMantenimiento(mantenimiento);
            return new ResponseEntity<>(nuevoMantenimiento, HttpStatus.CREATED);
        } catch (Exception e) {
            // Maneja la excepción de forma adecuada (log, mensaje de error, etc.)
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null); // o un mensaje de error
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mantenimiento> actualizarMantenimiento(@PathVariable ObjectId id, @Valid @RequestBody Mantenimiento mantenimiento) {
        if (!mantenimientoService.existeMantenimientoPorId(id)) {
            return ResponseEntity.notFound().build();
        }
        mantenimiento.setId(id);
        Mantenimiento mantenimientoActualizado = mantenimientoService.guardarMantenimiento(mantenimiento);
        return ResponseEntity.ok(mantenimientoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMantenimiento(@PathVariable ObjectId id) {
        if (!mantenimientoService.existeMantenimientoPorId(id)) {
            return ResponseEntity.notFound().build();
        }
        mantenimientoService.eliminarMantenimiento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/asignacion/{asignacionId}")
    public ResponseEntity<List<Mantenimiento>> obtenerMantenimientosPorAsignacion(@PathVariable ObjectId asignacionId) {
        return ResponseEntity.ok(mantenimientoService.obtenerMantenimientosPorAsignacion(asignacionId));
    }

    @GetMapping("/equipo/{equipoId}")
    public ResponseEntity<List<Mantenimiento>> obtenerMantenimientosPorEquipo(@PathVariable ObjectId equipoId) {
        return ResponseEntity.ok(mantenimientoService.obtenerMantenimientosPorEquipo(equipoId));
    }



}
