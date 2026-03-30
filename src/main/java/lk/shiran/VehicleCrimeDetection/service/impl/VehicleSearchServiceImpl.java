package lk.shiran.VehicleCrimeDetection.service.impl;

import lk.shiran.VehicleCrimeDetection.entity.VehicleSearch;
import lk.shiran.VehicleCrimeDetection.repo.VehicleSearchRepo;
import lk.shiran.VehicleCrimeDetection.service.VehicleSearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class VehicleSearchServiceImpl implements VehicleSearchService {
    private final VehicleSearchRepo repo;

    @Override
    public VehicleSearch save(VehicleSearch data) {
        return repo.save(data);
    }

    @Override
    public List<VehicleSearch> getAll() {
        return repo.findAll();
    }

    @Override
    public List<VehicleSearch> findByVehicleNo(String vehicleNo) {
        return repo.findByVehicleNo(vehicleNo);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
