package eu.mdabrowski.country_bfs;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Unreachable destination")
public class DestinationUnreachableException extends RuntimeException {
    public DestinationUnreachableException(String message) {
        super(message);
    }
}
