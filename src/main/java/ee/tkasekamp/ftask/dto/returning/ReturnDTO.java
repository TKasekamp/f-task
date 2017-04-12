package ee.tkasekamp.ftask.dto.returning;

import java.util.List;

public class ReturnDTO {
    private int costumerID;
    private List<ReturnItemDTO> items;

    public ReturnDTO(int costumerID, List<ReturnItemDTO> items) {
        this.costumerID = costumerID;
        this.items = items;
    }

    public int getCostumerID() {
        return costumerID;
    }

    public List<ReturnItemDTO> getItems() {
        return items;
    }
}
