package eu.mdabrowski.country_bfs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RouteFinderServiceTest {
    @Autowired
    RouteFinderService routeFinderService;

    @Test
    void shouldFindRoute() {
        // given
        String start = "CZE";
        String end = "ITA";

        // when
        List<String> route = routeFinderService.findRoute(start, end);

        // then
        assertThat(route).hasSize(3);
        assertThat(route).isEqualTo(List.of("CZE", "AUT", "ITA"));
    }

    @Test
    void shouldFindRouteWhenDestinationIsTheSameAsStart() {
        // given
        String start = "CZE";
        String end = "CZE";

        // when
        List<String> route = routeFinderService.findRoute(start, end);

        // then
        assertThat(route).hasSize(1);
        assertThat(route).isEqualTo(List.of("CZE"));
    }

    @Test
    void shouldThrowWhenDestinationCannotBeReached() {
        // given
        String start = "CZE";
        String end = "CAN";

        // expect
        assertThrows(DestinationUnreachableException.class, () -> routeFinderService.findRoute(start, end));
    }
}
