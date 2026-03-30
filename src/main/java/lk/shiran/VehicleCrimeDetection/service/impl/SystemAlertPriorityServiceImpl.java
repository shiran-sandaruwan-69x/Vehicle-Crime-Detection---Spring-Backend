package lk.shiran.VehicleCrimeDetection.service.impl;

import lk.shiran.VehicleCrimeDetection.entity.SystemAlertPriority;
import lk.shiran.VehicleCrimeDetection.repo.SystemAlertPriorityRepo;
import lk.shiran.VehicleCrimeDetection.service.SystemAlertPriorityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SystemAlertPriorityServiceImpl implements SystemAlertPriorityService {
    private final SystemAlertPriorityRepo repo;

    @Override
    public SystemAlertPriority save(SystemAlertPriority priority) {
        return repo.save(priority);
    }

    @Override
    public SystemAlertPriority update(Long id, SystemAlertPriority priority) {

        SystemAlertPriority existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Priority not found"));

        existing.setSystemAlertPriority(priority.getSystemAlertPriority());
        existing.setSystemAlertPriorityCode(priority.getSystemAlertPriorityCode());
        existing.setStatus(priority.getStatus());

        return repo.save(existing);
    }

    @Override
    public SystemAlertPriority get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Priority not found"));
    }

    @Override
    public List<SystemAlertPriority> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
