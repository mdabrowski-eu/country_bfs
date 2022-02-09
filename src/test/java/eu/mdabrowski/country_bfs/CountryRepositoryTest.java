package eu.mdabrowski.country_bfs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryRepositoryTest {
    @Autowired
    CountryRepository countryRepository;

    @Test
    void shouldLoadCountries() {
        // given
        // country repository was created and countries loaded from file

        // when
        Country country = countryRepository.createCountryNodeMap().get("POL");

        // then
        assertThat(country).isNotNull();
        assertThat(country.getCca3()).isEqualTo("POL");
        assertThat(country.getBorders()).isNotEmpty();
    }
}
