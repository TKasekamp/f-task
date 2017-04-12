package ee.tkasekamp.ftask.model;

/**
 * Created by Tõnis Kasekamp on 12.04.2017.
 */
public class Film {
    private int ID;
    private String name;
    private FilmType type;

    public Film(int ID, String name, FilmType type) {
        this.name = name;
        this.type = type;
        this.ID = ID;

    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FilmType getType() {
        return type;
    }

    public void setType(FilmType type) {
        this.type = type;
    }
}
