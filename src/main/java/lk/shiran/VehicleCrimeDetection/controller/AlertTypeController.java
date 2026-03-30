package lk.shiran.VehicleCrimeDetection.controller;

import lk.shiran.VehicleCrimeDetection.entity.AlertType;
import lk.shiran.VehicleCrimeDetection.service.AlertTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/alert-types")
@RequiredArgsConstructor
@CrossOrigin
public class AlertTypeController {
    private final AlertTypeService service;

    @PostMapping
    public ResponseEntity<AlertType> save(@RequestBody AlertType alertType) {
        return ResponseEntity.ok(service.save(alertType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertType> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<AlertType>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertType> update(
            @PathVariable Long id,
            @RequestBody AlertType alertType) {

        return ResponseEntity.ok(service.update(id, alertType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
