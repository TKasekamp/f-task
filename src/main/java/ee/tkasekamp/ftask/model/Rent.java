package ee.tkasekamp.ftask.model;

import java.time.LocalDate;

/**
 * There could be more data here, but filmID and endDate are the most important.
 */
public class Rent {
    private int filmID;
    private LocalDate endDate;

    public Rent(int filmID, LocalDate endDate) {
        this.filmID = filmID;
        this.endDate = endDate;
    }

    public int getFilmID() {
        return filmID;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}