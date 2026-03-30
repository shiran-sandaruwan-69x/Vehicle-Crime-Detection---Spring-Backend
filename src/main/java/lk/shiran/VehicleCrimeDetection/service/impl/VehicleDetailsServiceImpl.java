package lk.shiran.VehicleCrimeDetection.service.impl;

import lk.shiran.VehicleCrimeDetection.entity.VehicleDetails;
import lk.shiran.VehicleCrimeDetection.repo.VehicleDetailsRepo;
import lk.shiran.VehicleCrimeDetection.service.VehicleDetailsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class VehicleDetailsServiceImpl implements VehicleDetailsService {
    private final VehicleDetailsRepo vehicleRepo;

    @Override
    public VehicleDetails saveVehicle(VehicleDetails vehicle) {
        return vehicleRepo.save(vehicle);
    }

    @Override
    public VehicleDetails updateVehicle(Long id, VehicleDetails vehicle) {

        VehicleDetails existing = vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        existing.setVehicleNo(vehicle.getVehicleNo());
        existing.setVehicleType(vehicle.getVehicleType());
        existing.setVehicleBrand(vehicle.getVehicleBrand());
        existing.setVehicleColor(vehicle.getVehicleColor());
        existing.setManufactureYear(vehicle.getManufactureYear());
        existing.setOwnerName(vehicle.getOwnerName());
        existing.setOwnerNIC(vehicle.getOwnerNIC());
        existing.setStatus(vehicle.getStatus());

        return vehicleRepo.save(existing);
    }

    @Override
    public VehicleDetails getVehicle(Long id) {
        return vehicleRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }

    @Override
    public List<VehicleDetails> getAllVehicles() {
        return vehicleRepo.findAll();
    }

    @Override
    public void deleteVehicle(Long id) {
        vehicleRepo.deleteById(id);
    }
}
