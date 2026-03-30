package lk.shiran.VehicleCrimeDetection.controller;

import lk.shiran.VehicleCrimeDetection.entity.VehicleSearch;
import lk.shiran.VehicleCrimeDetection.service.VehicleSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@CrossOrigin
@RequestMapping("/api/vehicle-search")
public class VehicleSearchController {
    @Autowired
    private VehicleSearchService service;

    @PostMapping
    public ResponseEntity<VehicleSearch> save(@RequestBody VehicleSearch data) {
        return ResponseEntity.ok(service.save(data));
    }

    @GetMapping
    public ResponseEntity<List<VehicleSearch>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/search")
    public ResponseEntity<List<VehicleSearch>> search(@RequestParam String vehicleNo) {
        return ResponseEntity.ok(service.findByVehicleNo(vehicleNo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok("Deleted successfully");
    }
}
