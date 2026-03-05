package lk.shiran.VehicleCrimeDetection.repo;
import org.springframework.data.jpa.repository.JpaRepository;
import lk.shiran.VehicleCrimeDetection.entity.Module;
public interface ModuleRepo extends JpaRepository<Module, Long> {}