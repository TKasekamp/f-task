package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Tõnis Kasekamp on 12.04.2017.
 */

public class RentServiceTest {

    RentService rentService;

    @Before
    public void setup() {
        rentService = new RentService();
    }

    @Test
    public void calculatesNewFilmPrice() {
        Film film = new Film(1, "Rogue One", FilmType.NEW_RELEASE);

        int price1 = rentService.calculatePrice(film, 1);
        Assert.assertEquals(4, price1);

        int price2 = rentService.calculatePrice(film, 5);
        Assert.assertEquals(20, price2);
    }
}
