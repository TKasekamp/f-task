package ee.tkasekamp.ftask.dto;

import ee.tkasekamp.ftask.model.Film;

public class ReceiptItemDTO {
    private String filmName;
    private String filmType;
    private int days;
    private int extraDays;
    private int price;
    private boolean usedBonus;

    public ReceiptItemDTO(String filmName, String filmType, int days, int price) {
        this.filmName = filmName;
        this.filmType = filmType;
        this.days = days;
        this.price = price;
    }

    public ReceiptItemDTO(Film film, int days, int price, boolean usedBonus) {
        this.filmName = film.getName();
        this.filmType = film.getType().toString();
        this.days = days;
        this.price = price;
        this.usedBonus = false;
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

    public int getExtraDays() {
        return extraDays;
    }

    public int getPrice() {
        return price;
    }

    public boolean usedBonus() {
        return usedBonus;
    }
}
