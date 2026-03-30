package lk.shiran.VehicleCrimeDetection.service;

import lk.shiran.VehicleCrimeDetection.entity.Country;
import java.util.List;

public interface CountryService {
    Country save(Country country);

    Country update(Long id, Country country);

    Country get(Long id);

    List<Country> getAll();

    void delete(Long id);
}
