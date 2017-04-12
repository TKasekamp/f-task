package ee.tkasekamp.ftask.dto;

import java.time.LocalDate;

public class ReturnItemDTO {
    private int filmID;
    private LocalDate date;

    public ReturnItemDTO(int filmID, LocalDate date) {
        this.filmID = filmID;
        this.date = date;
    }

    public int getFilmID() {
        return filmID;
    }

    public LocalDate getDate() {
        return date;
    }

}
