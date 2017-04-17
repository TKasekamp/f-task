package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.dto.returning.ReturnDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnReceiptDTO;
import ee.tkasekamp.ftask.model.Customer;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;
import ee.tkasekamp.ftask.model.Rent;
import ee.tkasekamp.ftask.repository.Repository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReturnServiceTest {

    RentService rentService;
    FilmService filmService;
    CostumerService costumerService;
    ReturnService returnService;

    @Before
    public void setup() {
        rentService = new RentService();
        filmService = new FilmService();
        costumerService = new CostumerService();
        returnService = new ReturnService();

        List<Film> films = new ArrayList<>();
        films.add(new Film(0, "Film 0", FilmType.REGULAR));
        films.add(new Film(1, "Film 1", FilmType.NEW_RELEASE));
        films.add(new Film(2, "Film 2", FilmType.OLD));
        films.add(new Film(3, "Film 3", FilmType.REGULAR));
        films.add(new Film(4, "Film 4", FilmType.REGULAR));
        Repository.films.addAll(films);

        Customer c = new Customer(0);
        Repository.customers.add(c);

        // Create rents
        Repository.rents.add(new Rent(0, LocalDate.of(2017, 4, 15)));
        filmService.setAvailable(0, false);

        Repository.rents.add(new Rent(1, LocalDate.of(2017, 4, 15)));
        filmService.setAvailable(1, false);
    }

    @After
    public void tearDown() {
        Repository.customers.clear();
        Repository.films.clear();
        Repository.rents.clear();
    }

    @Test
    public void returnFilmsNormal() {
        ReturnDTO returnDTO = new ReturnDTO(0, LocalDate.of(2017, 4, 15), Arrays.asList(0));

        ReturnReceiptDTO receiptDTO = returnService.returnFilms(returnDTO);

        // Check output DTO
        Assert.assertEquals(0, receiptDTO.getTotal());
        Assert.assertEquals(1, receiptDTO.getItems().size());

        Assert.assertEquals(0, receiptDTO.getItems().get(0).getExtraDays());
        Assert.assertEquals(0, receiptDTO.getItems().get(0).getPrice());

        // Check film availability
        Assert.assertEquals(4, filmService.getAvailableFilms().size());

    }


    @Test
    public void returnFilmEarly() {

        ReturnDTO returnDTO = new ReturnDTO(0, LocalDate.of(2017, 4, 14), Arrays.asList(0));

        ReturnReceiptDTO receiptDTO = returnService.returnFilms(returnDTO);

        // Check output DTO
        Assert.assertEquals(0, receiptDTO.getTotal());
        Assert.assertEquals(1, receiptDTO.getItems().size());

        Assert.assertEquals(0, receiptDTO.getItems().get(0).getExtraDays());
        Assert.assertEquals(0, receiptDTO.getItems().get(0).getPrice());

        // Check film availability
        Assert.assertEquals(4, filmService.getAvailableFilms().size());

    }

    @Test
    public void returnFilmLateRegular() {

        ReturnDTO returnDTO = new ReturnDTO(0, LocalDate.of(2017, 4, 16), Arrays.asList(0));

        ReturnReceiptDTO receiptDTO = returnService.returnFilms(returnDTO);

        // Check output DTO
        Assert.assertEquals(3, receiptDTO.getTotal());
        Assert.assertEquals(1, receiptDTO.getItems().size());

        Assert.assertEquals(1, receiptDTO.getItems().get(0).getExtraDays());
        Assert.assertEquals(3, receiptDTO.getItems().get(0).getPrice());

        // Check film availability
        Assert.assertEquals(4, filmService.getAvailableFilms().size());

    }

    @Test
    public void returnFilmLateNewRelease() {

        ReturnDTO returnDTO = new ReturnDTO(0, LocalDate.of(2017, 4, 17), Arrays.asList(1));

        ReturnReceiptDTO receiptDTO = returnService.returnFilms(returnDTO);

        // Check output DTO
        Assert.assertEquals(8, receiptDTO.getTotal());
        Assert.assertEquals(1, receiptDTO.getItems().size());

        Assert.assertEquals(2, receiptDTO.getItems().get(0).getExtraDays());
        Assert.assertEquals(8, receiptDTO.getItems().get(0).getPrice());

        // Check film availability
        Assert.assertEquals(4, filmService.getAvailableFilms().size());

    }
}
