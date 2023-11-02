import lombok.Getter;

import java.util.List;

@Getter
public class FilmsPOJO {

    private String date;
    private List<String> characters;
    private List<String> planets;
    private List<String> starships;
    private List<String> vehicles;
    private List<String> species;

    public FilmsPOJO(String date, List<String> characters, List<String> planets, List<String> starships, List<String> vehicles, List<String> species) {
        this.date = date;
        this.characters = characters;
        this.planets = planets;
        this.starships = starships;
        this.vehicles = vehicles;
        this.species = species;
    }

    public FilmsPOJO() {
    }

}
