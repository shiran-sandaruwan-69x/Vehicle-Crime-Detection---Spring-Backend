package lk.shiran.VehicleCrimeDetection.repo;

import lk.shiran.VehicleCrimeDetection.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepo extends JpaRepository<Country, Long> {
}
