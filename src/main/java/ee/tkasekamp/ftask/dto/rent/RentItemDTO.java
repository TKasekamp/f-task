package ee.tkasekamp.ftask.dto.rent;

import java.time.LocalDate;

public class RentItemDTO {
    private int filmID;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean payWithBonus;

    public RentItemDTO(int filmID, LocalDate startDate, LocalDate endDate, boolean payWithBonus) {
        this.filmID = filmID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.payWithBonus = payWithBonus;
    }

    public int getFilmID() {
        return filmID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public boolean isPayWithBonus() {
        return payWithBonus;
    }
}
