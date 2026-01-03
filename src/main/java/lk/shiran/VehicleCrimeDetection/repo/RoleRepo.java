package lk.shiran.VehicleCrimeDetection.repo;

import lk.shiran.VehicleCrimeDetection.entity.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<AppRole,Long> {

    AppRole findByName(String name);
    

}
