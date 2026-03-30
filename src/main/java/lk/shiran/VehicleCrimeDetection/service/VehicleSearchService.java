package lk.shiran.VehicleCrimeDetection.service;

import lk.shiran.VehicleCrimeDetection.entity.VehicleSearch;
import java.util.List;
public interface VehicleSearchService {
    VehicleSearch save(VehicleSearch data);

    List<VehicleSearch> getAll();

    List<VehicleSearch> findByVehicleNo(String vehicleNo);

    void delete(Long id);
}
