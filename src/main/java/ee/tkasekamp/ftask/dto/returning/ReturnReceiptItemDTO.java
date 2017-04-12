package ee.tkasekamp.ftask.dto.returning;

import ee.tkasekamp.ftask.model.Film;

public class ReturnReceiptItemDTO {
    private String filmName;
    private String filmType;
    private int extraDays;
    private int price;

    public ReturnReceiptItemDTO(String filmName, String filmType, int extraDays, int price) {
        this.filmName = filmName;
        this.filmType = filmType;
        this.extraDays = extraDays;
        this.price = price;
    }

    public ReturnReceiptItemDTO(Film film, int extraDays, int price) {
        this.filmName = film.getName();
        this.filmType = film.getType().toString();
        this.extraDays = extraDays;
        this.price = price;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getFilmType() {
        return filmType;
    }

    public int getExtraDays() {
        return extraDays;
    }

    public int getPrice() {
        return price;
    }

}
