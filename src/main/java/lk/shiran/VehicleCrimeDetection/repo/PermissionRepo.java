package lk.shiran.VehicleCrimeDetection.repo;
import lk.shiran.VehicleCrimeDetection.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PermissionRepo extends JpaRepository<Permission, Long> {
    List<Permission> findByModuleId(Long moduleId);
}
