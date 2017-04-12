package ee.tkasekamp.ftask.model;

import java.time.LocalDate;

public class Rent {
    private int filmID;
    private int costumerID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int price;
    private boolean usedBonus;

    public Rent(int filmID, int costumerID, LocalDate startDate, LocalDate endDate, int price, boolean usedBonus) {
        this.filmID = filmID;
        this.costumerID = costumerID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.usedBonus = usedBonus;
    }
}