package eduzs.dev.flightapp.rest;

import eduzs.dev.flightapp.service.FlightService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/flight")
public class FlightRestApi {

    private final FlightService flightService;

    public FlightRestApi(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllFlights(@RequestParam String origin,
                                           @RequestParam String destination) {
        var flights = flightService.searchFlightsWithDetails(origin, destination);
        return ResponseEntity.ok(flights);
    }
}
