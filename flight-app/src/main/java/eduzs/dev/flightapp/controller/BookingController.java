package eduzs.dev.flightapp.controller;

import eduzs.dev.flightapp.model.Booking;
import eduzs.dev.flightapp.model.BookingWithDetails;
import eduzs.dev.flightapp.model.Passenger;
import eduzs.dev.flightapp.service.BookingService;
import eduzs.dev.flightapp.service.FlightService;
import eduzs.dev.flightapp.service.PassengerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final PassengerService passengerService;
    private final FlightService flightService;

    public BookingController(BookingService bookingService, PassengerService passengerService, FlightService flightService) {
        this.bookingService = bookingService;
        this.passengerService = passengerService;
        this.flightService = flightService;
    }

    @GetMapping
    public String listBookings(@RequestParam(required = false) String passengerId, Model model) {
        if (passengerId != null && !passengerId.isEmpty()) {
            try {
                Long passengerIdLong = Long.parseLong(passengerId);
                List<BookingWithDetails> bookings = bookingService.getPassengerBookingsWithDetails(passengerIdLong, passengerService, flightService);
                model.addAttribute("bookings", bookings);
                model.addAttribute("passengerId", passengerIdLong);
                model.addAttribute("filterActive", true);
            } catch (NumberFormatException e) {
                model.addAttribute("errorMessage", "Invalid passenger ID format");
                model.addAttribute("bookings", Collections.emptyList());
            }
        } else {
            List<BookingWithDetails> allBookings = bookingService.getAllBookingsWithDetails(passengerService, flightService);
            model.addAttribute("bookings", allBookings);
            model.addAttribute("filterActive", false);
        }
        
        return "bookings/list";
    }

    @PostMapping("/book")
    public String bookFlight(
            @RequestParam String flightNumber,
            @RequestParam String passengerId,
            @RequestParam String seatNumber,
            RedirectAttributes redirectAttributes) {
        
        try {
            Long passengerIdLong = Long.parseLong(passengerId);
            Booking booking = bookingService.bookFlight(flightNumber, passengerIdLong, seatNumber);
            redirectAttributes.addFlashAttribute("successMessage", 
                    "Flight booked successfully! Booking reference: " + booking.bookingReference());
            return "redirect:/bookings";
        } catch (NumberFormatException e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Invalid passenger ID format");
            return "redirect:/flights";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to book flight: " + e.getMessage());
            return "redirect:/flights";
        }
    }

    @PostMapping("/{bookingReference}/cancel")
    public String cancelBooking(
            @PathVariable String bookingReference,
            @RequestParam(required = false) String passengerId,
            RedirectAttributes redirectAttributes) {
        try {
            bookingService.cancelBooking(bookingReference);
            redirectAttributes.addFlashAttribute("successMessage", "Booking cancelled successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to cancel booking: " + e.getMessage());
        }
        if (passengerId != null && !passengerId.isEmpty()) {
            return "redirect:/bookings?passengerId=" + passengerId;
        } else {
            return "redirect:/bookings";
        }
    }

    @GetMapping("/{bookingReference}")
    public String viewBooking(@PathVariable String bookingReference, Model model, RedirectAttributes redirectAttributes) {
        try {
            var booking = bookingService.getBooking(bookingReference);
            if (booking.isPresent()) {
                model.addAttribute("booking", booking.get());
                Optional<Passenger> passenger = passengerService.findPassengerById(booking.get().passengerId());
                passenger.ifPresent(value -> model.addAttribute("passenger", value));
                return "bookings/view";
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Booking not found: " + bookingReference);
                return "redirect:/bookings";
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error retrieving booking: " + e.getMessage());
            return "redirect:/bookings";
        }
    }
}
