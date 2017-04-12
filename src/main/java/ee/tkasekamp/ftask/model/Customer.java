package ee.tkasekamp.ftask.model;

/**
 * Created by Tõnis Kasekamp on 12.04.2017.
 */
public class Customer {
    private int ID;
    private int bonusPoints;

    public Customer(int ID) {
        this.ID = ID;
        this.bonusPoints = 0;
    }

    public int getID() {
        return ID;
    }

    public int getBonusPoints() {
        return bonusPoints;
    }

    public void addPoints(int points) {
        this.bonusPoints += points;
    }

    public void removePoints(int points) {
        this.bonusPoints -= points;
    }
}
