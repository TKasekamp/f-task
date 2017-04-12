package ee.tkasekamp.ftask.dto.rent;

import java.util.List;

public class CreateRentDTO {
    private int costumerID;
    private List<RentItemDTO> items;

    public CreateRentDTO(int costumerID, List<RentItemDTO> items) {
        this.costumerID = costumerID;
        this.items = items;
    }

    public int getCostumerID() {
        return costumerID;
    }

    public List<RentItemDTO> getItems() {
        return items;
    }
}
