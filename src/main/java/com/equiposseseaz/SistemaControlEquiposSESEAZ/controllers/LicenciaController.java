package com.equiposseseaz.SistemaControlEquiposSESEAZ.controllers;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.models.Licencia;
import com.equiposseseaz.SistemaControlEquiposSESEAZ.services.LicenciaService;
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
@RequestMapping("/api/licencias")
public class LicenciaController {
    @Autowired
    private LicenciaService licenciaService;

    @GetMapping
    public List<Licencia> obtenerTodasLasLicencias() {
        return licenciaService.obtenerTodasLasLicencias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Licencia> obtenerLicenciaPorId(@PathVariable ObjectId id) {
        Optional<Licencia> licencia = licenciaService.obtenerLicenciaPorId(id);
        return licencia.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PostMapping
    public ResponseEntity<Licencia> crearLicencia(@RequestBody Licencia licencia) {
        return new ResponseEntity<>(licenciaService.guardarLicencia(licencia), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Licencia> actualizarLicencia(@PathVariable ObjectId id, @RequestBody Licencia licencia) {

        if (!licenciaService.existeLicenciaConId(id)) {
            return ResponseEntity.notFound().build();
        }
        licencia.setId(id);
        return new ResponseEntity<>(licenciaService.guardarLicencia(licencia), HttpStatus.OK);


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarLicencia(@PathVariable ObjectId id) {
        if (!licenciaService.existeLicenciaConId(id)) {
            return ResponseEntity.notFound().build();
        }
        licenciaService.eliminarLicenciaPorId(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Licencia>> buscarLicenciasPorNombre(@RequestParam("nombre") String nombre) {
        return ResponseEntity.ok(licenciaService.buscarLicenciasPorNombre(nombre));

    }

}
