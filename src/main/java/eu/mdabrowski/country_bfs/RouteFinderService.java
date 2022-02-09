package eu.mdabrowski.country_bfs;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteFinderService {
    private final CountryRepository countryRepository;

    public List<String> findRoute(String start, String end) {
        if (start.equals(end)) {
            return List.of(start);
        }

        Map<String, CountryNode> countryNodeMap = countryRepository.createCountryNodeMap();
        if (countryNodeMap.get(start) == null || countryNodeMap.get(end) == null) {
            throw new CountryDoesNotExistException();
        }
        Queue<CountryNode> queue = initQueue(start, countryNodeMap);
        while(!queue.isEmpty()){
            CountryNode current = queue.poll();
            List<CountryNode> unvisitedNeighbours = getUnvisitedNeighbours(countryNodeMap, current);
            for (CountryNode neighbour : unvisitedNeighbours) {
                neighbour.setVisited(true);
                neighbour.setPrevious(current.getCca3());
                queue.add(neighbour);
                if(neighbour.getCca3().equals(end)){
                    queue.clear();
                    break;
                }
            }
        }
        CountryNode destination = countryNodeMap.get(end);
        if (!destination.isVisited()) {
            throw new DestinationUnreachableException(String.format("Destination %s can't be reached from %s", end, start));
        }
        List<CountryNode> route = new ArrayList<>();
        while(destination != null){
            route.add(destination);
            destination = countryNodeMap.get(destination.getPrevious());
        }
        Collections.reverse(route);
        return route.stream().map(Country::getCca3).collect(Collectors.toList());
    }

    private Queue<CountryNode> initQueue(String start, Map<String, CountryNode> countryNodeMap) {
        Queue<CountryNode> queue = new LinkedList<>();
        CountryNode startingNode = countryNodeMap.get(start);
        startingNode.setVisited(true);
        queue.add(startingNode);
        return queue;
    }

    private List<CountryNode> getUnvisitedNeighbours(Map<String, CountryNode> countryNodeMap, CountryNode current) {
        return current.getBorders().stream()
                .map(countryNodeMap::get)
                .filter(n -> !n.isVisited())
                .collect(Collectors.toList());
    }
}
