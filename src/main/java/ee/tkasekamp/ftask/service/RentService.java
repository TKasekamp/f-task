package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.dto.RentOutputDTO;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

/**
 * Created by Tõnis Kasekamp on 12.04.2017.
 */
public class RentService {
    public final int BASIC_PRICE = 3;
    public final int PREMIUM_PRICE = 4;

    public RentOutputDTO rentFilm(int costumerID, int filmID, LocalDate startDate, LocalDate endDate) {
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
                break;
            case OLD:
                break;

        }
        return price;

    }

    private long dateDifference(LocalDate startDate, LocalDate endDate) {
        return DAYS.between(startDate, endDate);
    }
}
