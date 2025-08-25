package eduzs.dev.flightapp.repository;

import eduzs.dev.flightapp.model.Passenger;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PassengerRepository {

    private final JdbcClient jdbcClient;

    public PassengerRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Passenger> getAllPassengers() {
        return jdbcClient.sql("SELECT * FROM passenger ORDER BY last_name, first_name")
                .query(Passenger.class)
                .list();
    }

    public Optional<Passenger> findPassengerById(Long id) {
        return jdbcClient.sql("SELECT * FROM passenger WHERE id = ?")
                .param(id)
                .query(Passenger.class)
                .optional();
    }

    public Passenger createPassenger(String firstName, String lastName, String email, String phoneNumber, String passportNumber) {
        Long id = jdbcClient.sql("INSERT INTO passenger (first_name, last_name, email, phone_number, passport_number) " +
                        "VALUES (?, ?, ?, ?, ?) RETURNING id")
                .param(firstName)
                .param(lastName)
                .param(email)
                .param(phoneNumber)
                .param(passportNumber)
                .query(Long.class)
                .single();

        return new Passenger(id, firstName, lastName, email, phoneNumber, passportNumber);
    }

    public void updatePassenger(Long id, String firstName, String lastName, String email, String phoneNumber, String passportNumber) {
        jdbcClient.sql("UPDATE passenger SET first_name = ?, last_name = ?, email = ?, phone_number = ?, passport_number = ? " +
                        "WHERE id = ?")
                .param(firstName)
                .param(lastName)
                .param(email)
                .param(phoneNumber)
                .param(passportNumber)
                .param(id)
                .update();
    }

    public void deletePassenger(Long id) {
        int bookingCount = jdbcClient.sql("SELECT COUNT(*) FROM booking WHERE passenger_id = ?")
                .param(id)
                .query(Integer.class)
                .single();
                
        if (bookingCount > 0) {
            throw new IllegalStateException("Cannot delete passenger with existing bookings. Please cancel all bookings first.");
        }
        
        jdbcClient.sql("DELETE FROM passenger WHERE id = ?")
                .param(id)
                .update();
    }
}
