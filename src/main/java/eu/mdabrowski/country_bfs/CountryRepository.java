package eu.mdabrowski.country_bfs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class CountryRepository {

    private final List<Country> countries;

    public CountryRepository(@Value("classpath:countries.json") Resource countriesFile, ObjectMapper objectMapper) throws JsonProcessingException {
        this.countries = objectMapper.readValue(asString(countriesFile), new TypeReference<List<Country>>() {});
    }

    public Map<String, CountryNode> createCountryNodeMap() {
        Map<String, CountryNode> map = new HashMap<>();
        countries.forEach(c -> map.put(c.getCca3(), CountryNode.of(c)));
        return map;
    }

    private static String asString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
