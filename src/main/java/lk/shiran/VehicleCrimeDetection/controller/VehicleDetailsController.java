package lk.shiran.VehicleCrimeDetection.controller;

import lk.shiran.VehicleCrimeDetection.entity.VehicleDetails;
import lk.shiran.VehicleCrimeDetection.service.VehicleDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
@CrossOrigin
public class VehicleDetailsController {
    private final VehicleDetailsService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleDetails> save(@RequestBody VehicleDetails vehicle) {
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleDetails> get(@PathVariable Long id) {
        return ResponseEntity.ok(vehicleService.getVehicle(id));
    }

    @GetMapping
    public ResponseEntity<List<VehicleDetails>> getAll() {
        return ResponseEntity.ok(vehicleService.getAllVehicles());
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleDetails> update(
            @PathVariable Long id,
            @RequestBody VehicleDetails vehicle) {

        return ResponseEntity.ok(vehicleService.updateVehicle(id, vehicle));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}
