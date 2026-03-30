package lk.shiran.VehicleCrimeDetection.repo;

import lk.shiran.VehicleCrimeDetection.entity.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertTypeRepo extends JpaRepository<AlertType, Long> {
}
