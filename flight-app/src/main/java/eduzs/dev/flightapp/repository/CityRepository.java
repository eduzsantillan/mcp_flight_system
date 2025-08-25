package eduzs.dev.flightapp.repository;

import eduzs.dev.flightapp.model.City;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CityRepository {

    private final JdbcClient jdbcClient;

    public CityRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<City> getAllCities() {
        return jdbcClient.sql("""
                SELECT code, name, country_code as countryCode, description, timezone 
                FROM city ORDER BY name
                """)
                .query(City.class)
                .list();
    }

    public Optional<City> findCityByCode(String code) {
        return jdbcClient.sql("""
                SELECT code, name, country_code as countryCode, description, timezone 
                FROM city WHERE code = ?
                """)
                .param(code)
                .query(City.class)
                .optional();
    }
    
    public Optional<City> getFlightOriginCity(String flightNumber) {
        return jdbcClient.sql("""
                SELECT c.code, c.name, c.country_code as countryCode, c.description, c.timezone
                FROM city c
                JOIN flight f ON c.code = f.origin
                WHERE f.flight_number = ?
                """)
                .param(flightNumber)
                .query(City.class)
                .optional();
    }
    
    public Optional<City> getFlightDestinationCity(String flightNumber) {
        return jdbcClient.sql("""
                SELECT c.code, c.name, c.country_code as countryCode, c.description, c.timezone
                FROM city c
                JOIN flight f ON c.code = f.destination
                WHERE f.flight_number = ?
                """)
                .param(flightNumber)
                .query(City.class)
                .optional();
    }
}
