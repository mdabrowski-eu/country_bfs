package eu.mdabrowski.country_bfs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryNode extends Country {
    private boolean visited = false;
    private String previous;

    public static CountryNode of(Country country) {
        CountryNode countryNode = new CountryNode();
        countryNode.setVisited(false);
        countryNode.setBorders(country.getBorders());
        countryNode.setCca3(country.getCca3());

        return countryNode;
    }
}
