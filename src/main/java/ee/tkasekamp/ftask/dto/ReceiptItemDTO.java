package ee.tkasekamp.ftask.dto;

import ee.tkasekamp.ftask.model.Film;

public class ReceiptItemDTO {
    private String filmName;
    private String filmType;
    private int days;
    private int price;
    private int bonusPoints;

    public ReceiptItemDTO(Film film, int days, int price, int bonusPoints) {
        this.filmName = film.getName();
        this.filmType = film.getType().toString();
        this.days = days;
        this.price = price;
        this.bonusPoints = bonusPoints;

    }

    public String getFilmName() {
        return filmName;
    }

    public String getFilmType() {
        return filmType;
    }

    public int getDays() {
        return days;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        if (bonusPoints != 0) {
            return String.format("%s (%s) %d days (Paid with %d bonus points) and %d EUR", filmName, filmType, days, bonusPoints, price);
        } else {
            return String.format("%s (%s) %d days %d EUR", filmName, filmType, days, price);
        }
    }
}
