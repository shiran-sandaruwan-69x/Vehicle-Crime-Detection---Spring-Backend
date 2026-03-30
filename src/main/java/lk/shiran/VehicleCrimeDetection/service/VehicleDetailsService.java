package lk.shiran.VehicleCrimeDetection.service;

import lk.shiran.VehicleCrimeDetection.entity.VehicleDetails;

import java.util.List;

public interface VehicleDetailsService {
    VehicleDetails saveVehicle(VehicleDetails vehicle);

    VehicleDetails updateVehicle(Long id, VehicleDetails vehicle);

    VehicleDetails getVehicle(Long id);

    List<VehicleDetails> getAllVehicles();

    void deleteVehicle(Long id);
}
