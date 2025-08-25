package eduzs.dev.flightapp.service;

import eduzs.dev.flightapp.model.City;
import eduzs.dev.flightapp.model.Flight;
import eduzs.dev.flightapp.model.FlightWithDetails;
import eduzs.dev.flightapp.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlightService {

    private final FlightRepository flightRepository;

    public FlightService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Optional<Flight> findFlight(String flightNumber) {
        return flightRepository.findFlight(flightNumber);
    }

    public Optional<FlightWithDetails> findFlightWithDetails(String flightNumber) {
        return flightRepository.findFlightWithDetails(flightNumber);
    }

    public List<FlightWithDetails> searchFlightsWithDetails(String originCode, String destinationCode) {
        return flightRepository.searchFlightsWithDetailsWithoutDate(originCode, destinationCode);
    }

    public List<FlightWithDetails> getAllFlightsWithDetails() {
        return flightRepository.getAllFlightsWithDetails();
    }

    public Optional<City> getOriginCity(String flightNumber) {
        return flightRepository.getOriginCity(flightNumber);
    }

    public Optional<City> getDestinationCity(String flightNumber) {
        return flightRepository.getDestinationCity(flightNumber);
    }
}
