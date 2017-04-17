package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.dto.rent.CreateRentDTO;
import ee.tkasekamp.ftask.dto.rent.RentItemDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnItemDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnReceiptDTO;
import ee.tkasekamp.ftask.model.Customer;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;
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


        RentItemDTO r1 = new RentItemDTO(0, LocalDate.of(2017, 4, 12), LocalDate.of(2017, 4, 15), false);
        RentItemDTO r2 = new RentItemDTO(1, LocalDate.of(2017, 4, 12), LocalDate.of(2017, 4, 15), false);
        CreateRentDTO createRentDTO = new CreateRentDTO(0, Arrays.asList(r1, r2));
        rentService.rentFilms(createRentDTO);
    }

    @After
    public void tearDown() {
        Repository.customers.clear();
        Repository.films.clear();
        Repository.rents.clear();
    }

    @Test
    public void returnFilmsNormal() {
        ReturnItemDTO r1 = new ReturnItemDTO(0, LocalDate.of(2017, 4, 15));
        ReturnItemDTO r2 = new ReturnItemDTO(1, LocalDate.of(2017, 4, 15));

        Arrays.asList(r1, r2);

        ReturnDTO returnDTO = new ReturnDTO(0, Arrays.asList(r1, r2));

        ReturnReceiptDTO receiptDTO = returnService.returnFilms(returnDTO);

        // Check output DTO
        Assert.assertEquals(0, receiptDTO.getTotal());
        Assert.assertEquals(2, receiptDTO.getItems().size());

        Assert.assertEquals(0, receiptDTO.getItems().get(0).getExtraDays());
        Assert.assertEquals(0, receiptDTO.getItems().get(0).getPrice());

        Assert.assertEquals(0, receiptDTO.getItems().get(1).getExtraDays());
        Assert.assertEquals(0, receiptDTO.getItems().get(1).getPrice());

        // Check film availability
        Assert.assertEquals(5, filmService.getAvailableFilms().size());

        // Check bonus points
        Assert.assertEquals(3, costumerService.getBonusPoints(0));

        // Check rents created
        Assert.assertEquals(2, Repository.rents.size());
    }

}
