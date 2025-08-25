package eduzs.dev.flightapp.service;

import eduzs.dev.flightapp.model.Passenger;
import eduzs.dev.flightapp.repository.PassengerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PassengerService {

    private final PassengerRepository passengerRepository;

    public PassengerService(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    public List<Passenger> getAllPassengers() {
        return passengerRepository.getAllPassengers();
    }

    public Optional<Passenger> findPassengerById(Long id) {
        return passengerRepository.findPassengerById(id);
    }

    public Passenger createPassenger(String firstName, String lastName, String email, String phoneNumber, String passportNumber) {
        return passengerRepository.createPassenger(firstName, lastName, email, phoneNumber, passportNumber);
    }

    public void updatePassenger(Long id, String firstName, String lastName, String email, String phoneNumber, String passportNumber) {
        passengerRepository.updatePassenger(id, firstName, lastName, email, phoneNumber, passportNumber);
    }

    public void deletePassenger(Long id) {
        passengerRepository.deletePassenger(id);
    }
}
