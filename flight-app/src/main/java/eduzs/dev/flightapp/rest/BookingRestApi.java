package eduzs.dev.flightapp.rest;

import eduzs.dev.flightapp.rest.model.BookingApiRequest;
import eduzs.dev.flightapp.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
public class BookingRestApi {

    private final BookingService bookingService;

    public BookingRestApi(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping
    public ResponseEntity<?> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingApiRequest request) {
        if (request == null || request.flightNumber() == null || request.passengerId() == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(bookingService.bookFlight(request.flightNumber(), request.passengerId(), request.seatNumber()));
    }

}
