package eduzs.dev.flightapp.rest;

import eduzs.dev.flightapp.service.PassengerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/passengers")
public class PassengerRestApi {

    private final PassengerService passengerService;

    public PassengerRestApi(PassengerService passengerService) {
        this.passengerService = passengerService;
    }

    @GetMapping
    public ResponseEntity<?> getAllPassengers() {
        return ResponseEntity.ok(passengerService.getAllPassengers());
    }
}
