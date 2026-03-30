package lk.shiran.VehicleCrimeDetection.controller;

import lk.shiran.VehicleCrimeDetection.entity.Country;
import lk.shiran.VehicleCrimeDetection.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
@CrossOrigin
public class CountryController {
    private final CountryService service;

    @PostMapping
    public ResponseEntity<Country> save(@RequestBody Country country) {
        return ResponseEntity.ok(service.save(country));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.get(id));
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Country> update(
            @PathVariable Long id,
            @RequestBody Country country) {

        return ResponseEntity.ok(service.update(id, country));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
