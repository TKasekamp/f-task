package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.dto.ReceiptDTO;
import ee.tkasekamp.ftask.dto.rent.CreateRentDTO;
import ee.tkasekamp.ftask.dto.rent.RentItemDTO;
import ee.tkasekamp.ftask.model.Customer;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;
import ee.tkasekamp.ftask.repository.Repository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class RentServiceTest {

    RentService rentService;
    FilmService filmService;
    CostumerService costumerService;

    @Before
    public void setup() {
        rentService = new RentService();
        filmService = new FilmService();
        costumerService = new CostumerService();

        List<Film> films = new ArrayList<>();
        films.add(new Film(0, "Film 0", FilmType.REGULAR));
        films.add(new Film(1, "Film 1", FilmType.NEW_RELEASE));
        films.add(new Film(2, "Film 2", FilmType.OLD));
        films.add(new Film(3, "Film 3", FilmType.REGULAR));
        films.add(new Film(4, "Film 4", FilmType.REGULAR));
        Repository.films.addAll(films);

        Customer c = new Customer(0);
        Repository.customers.add(c);
    }

    @Test
    public void rentFilmsNormal() {
        RentItemDTO r1 = new RentItemDTO(0, LocalDate.of(2017, 4, 12), LocalDate.of(2017, 4, 15), false);
        RentItemDTO r2 = new RentItemDTO(1, LocalDate.of(2017, 4, 12), LocalDate.of(2017, 4, 15), false);

        CreateRentDTO createRentDTO = new CreateRentDTO(0, Arrays.asList(r1, r2));

        ReceiptDTO receiptDTO = rentService.rentFilms(createRentDTO);

        // Check output DTO
        Assert.assertFalse(receiptDTO.isUsedBonus());
        Assert.assertEquals(15, receiptDTO.getTotal());
        Assert.assertEquals(3, receiptDTO.getBonusPointsRemaining());
        Assert.assertEquals(2, receiptDTO.getItems().size());

        // Check film availability
        Assert.assertEquals(3, filmService.getAvailableFilms().size());

        // Check bonus points
        Assert.assertEquals(3, costumerService.getBonusPoints(0));

        // Check rents created
        Assert.assertEquals(2, Repository.rents.size());
//        Assert.assertEquals(1, Repository.rents.get(0));
    }

    @Test
    public void calculatesNewFilmPrice() {
        Film film = new Film(1, "Rogue One", FilmType.NEW_RELEASE);

        int price1 = rentService.calculatePrice(film, 1);
        Assert.assertEquals(4, price1);

        int price2 = rentService.calculatePrice(film, 5);
        Assert.assertEquals(20, price2);
    }

    @Test
    public void calculatesRegularFilmPrice() {
        Film film = new Film(1, "Rogue One", FilmType.REGULAR);

        int price1 = rentService.calculatePrice(film, 1);
        Assert.assertEquals(3, price1);

        int price2 = rentService.calculatePrice(film, 3);
        Assert.assertEquals(3, price2);

        int price3 = rentService.calculatePrice(film, 4);
        Assert.assertEquals(6, price3);

        int price4 = rentService.calculatePrice(film, 6);
        Assert.assertEquals(12, price4);
    }

    @Test
    public void calculatesOldFilmPrice() {
        Film film = new Film(1, "Rogue One", FilmType.OLD);

        int price1 = rentService.calculatePrice(film, 1);
        Assert.assertEquals(3, price1);

        int price2 = rentService.calculatePrice(film, 5);
        Assert.assertEquals(3, price2);

        int price3 = rentService.calculatePrice(film, 6);
        Assert.assertEquals(6, price3);

        int price4 = rentService.calculatePrice(film, 8);
        Assert.assertEquals(12, price4);
    }
}
