package lk.shiran.VehicleCrimeDetection.controller;

import lk.shiran.VehicleCrimeDetection.entity.SystemAlertPriority;
import lk.shiran.VehicleCrimeDetection.service.SystemAlertPriorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/priorities")
@RequiredArgsConstructor
@CrossOrigin
public class SystemAlertPriorityController {
    private final SystemAlertPriorityService service;

    @PostMapping
    public ResponseEntity<SystemAlertPriority> save(@RequestBody SystemAlertPriority priority) {
        return ResponseEntity.ok(service.save(priority));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SystemAlertPriority> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<SystemAlertPriority>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SystemAlertPriority> update(
            @PathVariable Long id,
            @RequestBody SystemAlertPriority priority) {

        return ResponseEntity.ok(service.update(id, priority));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
