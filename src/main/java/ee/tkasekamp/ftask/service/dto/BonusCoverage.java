package ee.tkasekamp.ftask.service.dto;

/**
 * A DTO to return a pair of results.
 */
public class BonusCoverage {
    private int bonusPointsUsed;
    private int payableDays;

    public BonusCoverage(int bonusPointsUsed, int payableDays) {
        this.bonusPointsUsed = bonusPointsUsed;
        this.payableDays = payableDays;

    }

    public int getBonusPointsUsed() {
        return bonusPointsUsed;
    }

    public int getPayableDays() {
        return payableDays;
    }
}
