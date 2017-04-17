package ee.tkasekamp.ftask.dto.returning;

import java.time.LocalDate;
import java.util.List;

public class ReturnDTO {
    private int costumerID;
    private LocalDate date;
    private List<Integer> items;

    public ReturnDTO(int costumerID, LocalDate date, List<Integer> items) {
        this.costumerID = costumerID;
        this.date = date;
        this.items = items;
    }

    public int getCostumerID() {
        return costumerID;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Integer> getItems() {
        return items;
    }
}
