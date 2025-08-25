package eduzs.dev.flightapp.service;

import eduzs.dev.flightapp.model.City;
import eduzs.dev.flightapp.repository.CityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    private final CityRepository cityRepository;

    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public List<City> getAllCities() {
        return cityRepository.getAllCities();
    }
}
