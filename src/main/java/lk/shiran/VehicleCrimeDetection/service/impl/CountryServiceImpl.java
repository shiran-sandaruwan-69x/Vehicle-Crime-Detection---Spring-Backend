package lk.shiran.VehicleCrimeDetection.service.impl;

import lk.shiran.VehicleCrimeDetection.entity.Country;
import lk.shiran.VehicleCrimeDetection.repo.CountryRepo;
import lk.shiran.VehicleCrimeDetection.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.*;
import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepo repo;

    @Override
    public Country save(Country country) {
        return repo.save(country);
    }

    @Override
    public Country update(Long id, Country country) {

        Country existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        existing.setCounties(country.getCounties());
        existing.setCountryCode(country.getCountryCode());
        existing.setStatus(country.getStatus());

        return repo.save(existing);
    }

    @Override
    public Country get(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));
    }

    @Override
    public List<Country> getAll() {
        return repo.findAll();
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
