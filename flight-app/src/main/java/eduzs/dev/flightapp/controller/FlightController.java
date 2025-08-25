package eduzs.dev.flightapp.controller;

import eduzs.dev.flightapp.model.FlightWithDetails;
import eduzs.dev.flightapp.service.FlightService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public String getAllFlights(Model model) {
        List<FlightWithDetails> allFlights = flightService.getAllFlightsWithDetails();
        model.addAttribute("flights", allFlights);
        return "flights/list";
    }

    @GetMapping("/search")
    public String searchFlights(
            @RequestParam String origin,
            @RequestParam String destination,
            Model model) {

        List<FlightWithDetails> flights = flightService.searchFlightsWithDetails(origin, destination);
        model.addAttribute("flights", flights);
        model.addAttribute("origin", origin);
        model.addAttribute("destination", destination);

        return "flights/results";
    }

    @GetMapping("/{flightNumber}")
    public String getFlightDetails(@PathVariable String flightNumber, Model model) {
        Optional<FlightWithDetails> flight = flightService.findFlightWithDetails(flightNumber);
        
        if (flight.isEmpty()) {
            return "redirect:/flights";
        }
        
        model.addAttribute("flight", flight.get());
        return "flights/details";
    }
}
