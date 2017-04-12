package ee.tkasekamp.ftask.dto;

import ee.tkasekamp.ftask.model.Film;

public class ReceiptItemDTO {
    private String filmName;
    private String filmType;
    private int days;
    private int extraDays;
    private int price;

    public ReceiptItemDTO(String filmName, String filmType, int days, int price) {
        this.filmName = filmName;
        this.filmType = filmType;
        this.days = days;
        this.price = price;
    }

    public ReceiptItemDTO(Film film, int days, int price) {
        this.filmName = film.getName();
        this.filmType = film.getType().toString();
        this.days = days;
        this.price = price;
    }
}
