package ee.tkasekamp.ftask.dto.returning;

import java.time.LocalDate;
import java.util.List;

public class ReturnDTO {
    private LocalDate date;
    private List<Integer> items;

    public ReturnDTO(LocalDate date, List<Integer> items) {
        this.date = date;
        this.items = items;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Integer> getItems() {
        return items;
    }
}
