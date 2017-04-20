package ee.tkasekamp.ftask.dto;

import java.util.List;

public class ReceiptDTO {
    private long total;
    private int bonusPointsRemaining;
    private List<ReceiptItemDTO> items;

    public ReceiptDTO(long total, int bonusPointsRemaining, List<ReceiptItemDTO> items) {
        this.total = total;
        this.bonusPointsRemaining = bonusPointsRemaining;
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public List<ReceiptItemDTO> getItems() {
        return items;
    }

    public int getBonusPointsRemaining() {
        return bonusPointsRemaining;
    }

}
