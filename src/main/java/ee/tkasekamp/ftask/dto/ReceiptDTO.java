package ee.tkasekamp.ftask.dto;

import java.util.List;

public class ReceiptDTO {
    private long total;
    private boolean usedBonus;
    private int bonusPointsRemaining;
    private List<ReceiptItemDTO> items;

    public ReceiptDTO(long total, boolean usedBonus, int bonusPointsRemaining, List<ReceiptItemDTO> items) {
        this.total = total;
        this.usedBonus = usedBonus;
        this.bonusPointsRemaining = bonusPointsRemaining;
        this.items = items;
    }

    public long getTotal() {
        return total;
    }

    public List<ReceiptItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ReceiptItemDTO> items) {
        this.items = items;
    }

    public boolean isUsedBonus() {
        return usedBonus;
    }


    public int getBonusPointsRemaining() {
        return bonusPointsRemaining;
    }

}
