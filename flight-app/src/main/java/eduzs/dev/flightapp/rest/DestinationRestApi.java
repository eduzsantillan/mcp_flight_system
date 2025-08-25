package eduzs.dev.flightapp.rest;

import eduzs.dev.flightapp.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/destinations")
public class DestinationRestApi {

    private final CityService cityService;

    public DestinationRestApi(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping
    public ResponseEntity<?> getDestinations() {
        return ResponseEntity.ok(cityService.getAllCities());
    }
}
