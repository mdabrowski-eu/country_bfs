package eu.mdabrowski.country_bfs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested country does not exist")
public class CountryDoesNotExistException extends RuntimeException {
}
