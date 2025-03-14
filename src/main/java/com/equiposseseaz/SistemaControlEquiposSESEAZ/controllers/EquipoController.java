package com.equiposseseaz.SistemaControlEquiposSESEAZ.controllers;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Equipo;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.services.EquipoService;
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
@RequestMapping("/api/equipos")
public class EquipoController {
    @Autowired
    private EquipoService equipoService;

    @GetMapping
    public ResponseEntity<List<Equipo>> obtenerTodosLosEquipos() {
        return ResponseEntity.ok(equipoService.obtenerTodosLosEquipos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipo> obtenerEquipoPorId(@PathVariable ObjectId id) {
        Optional<Equipo> equipo = equipoService.obtenerEquipoPorId(id);
        return equipo.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Equipo> crearEquipo(@Valid @RequestBody Equipo equipo) {
        Equipo nuevoEquipo = equipoService.crearEquipo(equipo);
        return new ResponseEntity<>(nuevoEquipo, HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipo> actualizarEquipo(@PathVariable ObjectId id, @Valid @RequestBody Equipo equipo) {
        if (!equipoService.existeEquipoPorId(id)) {
            return ResponseEntity.notFound().build();
        }

        equipo.setId(id);
        Equipo equipoActualizado = equipoService.actualizarEquipo(equipo);
        return ResponseEntity.ok(equipoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEquipo(@PathVariable ObjectId id) {
        if (!equipoService.existeEquipoPorId(id)) {
            return ResponseEntity.notFound().build();
        }
        equipoService.eliminarEquipo(id);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/buscarSerie")
    public ResponseEntity<Equipo> buscarEquipoPorNumeroDeSerie(@RequestParam("serieEquipo") String numeroSerie) {
        return ResponseEntity.ok(equipoService.buscarEquipoPorNumeroDeSerie(numeroSerie));
    }

    @GetMapping("/buscarMarca")
    public ResponseEntity<Equipo> buscarEquipoPorMarca(@RequestParam("marcaEquipo") String marcaEquipo) {
        return ResponseEntity.ok(equipoService.buscarEquipoPorMarca(marcaEquipo));
    }

    @GetMapping("/buscarModelo")
    public ResponseEntity<Equipo> buscarEquipoPorModelo(@RequestParam("modeloEquipo") String modeloEquipo) {
        return ResponseEntity.ok(equipoService.buscarEquipoPorMarca(modeloEquipo));
    }


}
