package ee.tkasekamp.ftask.dto;

public class ReceiptDTO {
    private int total;
    private boolean usedBonus;
    private int bonusPointsRemaining;

    public ReceiptDTO(int total, boolean usedBonus, int bonusPointsRemaining) {
        this.total = total;
        this.usedBonus = usedBonus;
        this.bonusPointsRemaining = bonusPointsRemaining;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isUsedBonus() {
        return usedBonus;
    }

    public void setUsedBonus(boolean usedBonus) {
        this.usedBonus = usedBonus;
    }

    public int getBonusPointsRemaining() {
        return bonusPointsRemaining;
    }

    public void setBonusPointsRemaining(int bonusPointsRemaining) {
        this.bonusPointsRemaining = bonusPointsRemaining;
    }
}
