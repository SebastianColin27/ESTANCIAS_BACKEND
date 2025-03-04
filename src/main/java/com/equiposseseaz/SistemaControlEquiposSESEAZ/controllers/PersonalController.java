package com.equiposseseaz.SistemaControlEquiposSESEAZ.controllers;

import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Personal;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.services.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.bson.types.ObjectId;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal")
public class PersonalController {

    @Autowired
    private PersonalService personalService;

    @GetMapping
    public List<Personal> obtenerTodoElPersonal() {
        return personalService.obtenerTodoElPersonal();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personal> obtenerPersonalPorId(@PathVariable ObjectId id) {
        return personalService.obtenerPersonalPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Personal> crearPersonal(@Valid @RequestBody Personal personal) {
        Personal nuevoPersonal = personalService.crearPersonal(personal);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPersonal);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personal> actualizarPersonal(@PathVariable ObjectId id, @Valid @RequestBody Personal personal) {
        if (!personalService.existePersonalPorId(id)) {
            return ResponseEntity.notFound().build();
        }
        personal.setId(id);
        Personal personalActualizado = personalService.actualizarPersonal(personal);
        return ResponseEntity.ok(personalActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersonal(@PathVariable ObjectId id) {
        if (!personalService.existePersonalPorId(id)) {
            return ResponseEntity.notFound().build();
        }
        personalService.eliminarPersonal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Personal>> buscarPersonalPorNombre(@RequestParam("nombre") String nombre) {
        return ResponseEntity.ok(personalService.buscarPersonalPorNombre(nombre));
    }
}
