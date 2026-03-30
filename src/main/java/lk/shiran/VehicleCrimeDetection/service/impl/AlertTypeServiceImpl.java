package lk.shiran.VehicleCrimeDetection.service.impl;

import lk.shiran.VehicleCrimeDetection.entity.AlertType;
import lk.shiran.VehicleCrimeDetection.repo.AlertTypeRepo;
import lk.shiran.VehicleCrimeDetection.service.AlertTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AlertTypeServiceImpl implements AlertTypeService {
    private final AlertTypeRepo repo;

    @Override
    public AlertType save(AlertType alertType) {
        return repo.save(alertType);
    }

    @Override
    public AlertType update(Long id, AlertType alertType) {

        AlertType existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("AlertType not found"));

        existing.setAlertType(alertType.getAlertType());
        existing.setAlertTypeCode(alertType.getAlertTypeCode());
        existing.setStatus(alertType.getStatus());

        return repo.save(existing);
    }

    @Override
    public AlertType get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("AlertType not found"));
    }

    @Override
    public List<AlertType> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
