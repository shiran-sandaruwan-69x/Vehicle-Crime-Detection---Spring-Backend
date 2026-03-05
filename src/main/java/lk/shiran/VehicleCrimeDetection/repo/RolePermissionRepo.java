package lk.shiran.VehicleCrimeDetection.repo;
import lk.shiran.VehicleCrimeDetection.entity.RolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RolePermissionRepo extends JpaRepository<RolePermission, Long> {
    List<RolePermission> findByRoleId(Long roleId);
    void deleteByRoleId(Long roleId);
}
