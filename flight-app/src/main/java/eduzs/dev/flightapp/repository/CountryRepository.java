package eduzs.dev.flightapp.repository;

import eduzs.dev.flightapp.model.Country;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class CountryRepository {

    private final JdbcClient jdbcClient;

    public CountryRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }
    
    public Optional<Country> getCountryByCity(String cityCode) {
        return jdbcClient.sql("""
                SELECT co.* FROM country co
                JOIN city ci ON co.code = ci.country_code
                WHERE ci.code = ?
                """)
                .param(cityCode)
                .query(Country.class)
                .optional();
    }
}
