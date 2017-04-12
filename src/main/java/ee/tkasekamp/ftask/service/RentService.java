package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.dto.ReceiptDTO;
import ee.tkasekamp.ftask.dto.ReceiptItemDTO;
import ee.tkasekamp.ftask.dto.rent.CreateRentDTO;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

public class RentService {
    public final int BASIC_PRICE = 3;
    public final int PREMIUM_PRICE = 4;
    public final int REGULAR_PRICE_DAYS = 3;
    public final int OLD_PRICE_DAYS = 5;

    public ReceiptDTO rentFilms(CreateRentDTO dto) {
        return null;
    }

    public ReceiptItemDTO rentFilm(int costumerID, int filmID, LocalDate startDate, LocalDate endDate) {
        long days = dateDifference(startDate, endDate);
        return null;
    }

    public int calculatePrice(Film film, int days) {
        FilmType type = film.getType();
        int price = 0;
        switch (type) {
            case NEW_RELEASE:
                price = PREMIUM_PRICE * days;
                break;
            case REGULAR:
                price = calculateRegularPrice(days);
                break;
            case OLD:
                price = calculateOldPrice(days);
                break;

        }
        return price;

    }

    private int calculateRegularPrice(int days) {
        int price = BASIC_PRICE;

        int overDays = days - REGULAR_PRICE_DAYS;

        if (overDays > 0) {
            price += overDays * BASIC_PRICE;
        }

        return price;
    }

    private int calculateOldPrice(int days) {
        int price = BASIC_PRICE;

        int overDays = days - OLD_PRICE_DAYS;

        if (overDays > 0) {
            price += overDays * BASIC_PRICE;
        }

        return price;
    }

    private long dateDifference(LocalDate startDate, LocalDate endDate) {
        return DAYS.between(startDate, endDate);
    }
}
