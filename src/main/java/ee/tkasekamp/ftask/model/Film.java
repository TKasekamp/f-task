package ee.tkasekamp.ftask.model;

/**
 * Created by Tõnis Kasekamp on 12.04.2017.
 */
public class Film {
    private int ID;
    private String name;
    private FilmType type;
    private boolean available;

    public Film(int ID, String name, FilmType type) {
        this.name = name;
        this.type = type;
        this.ID = ID;
        this.available = true;

    }

    public int getID() {
        return ID;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
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
