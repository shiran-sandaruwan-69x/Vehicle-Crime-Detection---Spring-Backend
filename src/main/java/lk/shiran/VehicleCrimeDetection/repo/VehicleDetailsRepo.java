package lk.shiran.VehicleCrimeDetection.repo;

import lk.shiran.VehicleCrimeDetection.entity.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleDetailsRepo extends JpaRepository<VehicleDetails, Long> {
}
