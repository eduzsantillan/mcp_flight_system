package eduzs.dev.flightapp.controller;

import eduzs.dev.flightapp.model.Booking;
import eduzs.dev.flightapp.model.FlightWithDetails;
import eduzs.dev.flightapp.model.Passenger;
import eduzs.dev.flightapp.service.BookingService;
import eduzs.dev.flightapp.service.FlightService;
import eduzs.dev.flightapp.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/seats")
public class SeatController {

    private final FlightService flightService;
    private final PassengerService passengerService;
    private final BookingService bookingService;

    public SeatController(FlightService flightService, PassengerService passengerService, BookingService bookingService) {
        this.flightService = flightService;
        this.passengerService = passengerService;
        this.bookingService = bookingService;
    }

    @GetMapping("/select/{flightNumber}")
    public String selectSeat(@PathVariable String flightNumber, Model model) {
        Optional<FlightWithDetails> flight = flightService.findFlightWithDetails(flightNumber);
        
        if (flight.isEmpty()) {
            return "redirect:/flights";
        }
        
        List<Passenger> passengers = passengerService.getAllPassengers();
        List<String> bookedSeats = bookingService.getFlightBookings(flightNumber).stream()
                .map(Booking::seatNumber)
                .toList();
        
        model.addAttribute("flight", flight.get());
        model.addAttribute("passengers", passengers);
        model.addAttribute("bookedSeats", bookedSeats);
        
        return "seats/select";
    }

    @PostMapping("/book")
    public String bookSeat(
            @RequestParam String flightNumber,
            @RequestParam Long passengerId,
            @RequestParam String seatNumber,
            RedirectAttributes redirectAttributes) {
        
        try {
            Booking booking = bookingService.bookFlight(flightNumber, passengerId, seatNumber);
            redirectAttributes.addFlashAttribute("message", "Seat booked successfully. Booking reference: " + booking.bookingReference());
            return "redirect:/bookings/" + booking.bookingReference();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/seats/select/" + flightNumber;
        }
    }
}
