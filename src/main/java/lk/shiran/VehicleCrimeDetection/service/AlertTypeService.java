package lk.shiran.VehicleCrimeDetection.service;

import lk.shiran.VehicleCrimeDetection.entity.AlertType;
import java.util.List;

public interface AlertTypeService {
    AlertType save(AlertType alertType);

    AlertType update(Long id, AlertType alertType);

    AlertType get(Long id);

    List<AlertType> getAll();

    void delete(Long id);
}
