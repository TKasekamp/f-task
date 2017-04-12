package ee.tkasekamp.ftask.model;

/**
 * Created by Tõnis Kasekamp on 12.04.2017.
 */
public class Film {
    private String name;
    private FilmType type;

    public Film(String name, FilmType type) {
        this.name = name;
        this.type = type;
    }
}
