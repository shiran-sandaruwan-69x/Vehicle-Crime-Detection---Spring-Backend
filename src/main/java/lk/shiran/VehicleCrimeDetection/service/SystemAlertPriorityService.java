package lk.shiran.VehicleCrimeDetection.service;

import lk.shiran.VehicleCrimeDetection.entity.SystemAlertPriority;
import java.util.List;
public interface SystemAlertPriorityService {
    SystemAlertPriority save(SystemAlertPriority priority);

    SystemAlertPriority update(Long id, SystemAlertPriority priority);

    SystemAlertPriority get(Long id);

    List<SystemAlertPriority> getAll();

    void delete(Long id);
}
