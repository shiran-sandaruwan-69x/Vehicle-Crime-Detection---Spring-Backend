package lk.shiran.VehicleCrimeDetection.repo;

import lk.shiran.VehicleCrimeDetection.entity.VehicleSearch;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface VehicleSearchRepo extends JpaRepository<VehicleSearch, Long> {
    List<VehicleSearch> findByVehicleNo(String vehicleNo);
}
