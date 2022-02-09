package eu.mdabrowski.country_bfs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping(value = "/routing", produces = APPLICATION_JSON_VALUE)
public class RoutingController {
    private final RouteFinderService routeFinderService;

    @GetMapping("/{start}/{end}")
    public RoutingDTO getRoute(@PathVariable String start, @PathVariable String end) {
        return new RoutingDTO(routeFinderService.findRoute(start, end));
    }
}
