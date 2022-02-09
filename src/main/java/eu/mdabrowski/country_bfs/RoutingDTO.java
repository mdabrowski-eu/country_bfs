package eu.mdabrowski.country_bfs;

import lombok.Data;
import lombok.Getter;

import java.util.List;

public class RoutingDTO {
    @Getter
    private final List<String> route;

    public RoutingDTO(List<String> route) {
        this.route = route;
    }
}
