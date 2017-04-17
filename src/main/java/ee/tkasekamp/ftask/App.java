package ee.tkasekamp.ftask;

import ee.tkasekamp.ftask.dto.rent.CreateRentDTO;
import ee.tkasekamp.ftask.dto.rent.RentItemDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnDTO;
import ee.tkasekamp.ftask.model.Customer;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;
import ee.tkasekamp.ftask.repository.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tõnis Kasekamp on 17.04.2017.
 */
public class App {

    private static FilmRentController controller;

    public static void main(String[] args) {
        controller = new FilmRentController();
        addSampleData();

        rentFilmsNormally();

        rentFilmsWithBonus();
        returnFilmEarly();
        returnFilmOnTime();
        returnFilmLate();

    }

    private static void rentFilmsNormally() {
        System.out.println("RENT FILMS");
        System.out.println("----------");
        RentItemDTO r1 = new RentItemDTO(0, LocalDate.of(2017, 4, 12), LocalDate.of(2017, 4, 15), false);
        RentItemDTO r2 = new RentItemDTO(1, LocalDate.of(2017, 4, 12), LocalDate.of(2017, 4, 15), false);

        CreateRentDTO createRentDTO = new CreateRentDTO(0, Arrays.asList(r1, r2));

        controller.rentFilms(createRentDTO);
        System.out.println("----------");
    }

    private static void rentFilmsWithBonus() {
        System.out.println("RENT FILMS WITH BONUS, PARTIAL PAYMENT");
        System.out.println("----------");

        RentItemDTO r1 = new RentItemDTO(4, LocalDate.of(2017, 4, 12), LocalDate.of(2017, 4, 15), true);

        CreateRentDTO createRentDTO = new CreateRentDTO(0, Arrays.asList(r1));

        controller.rentFilms(createRentDTO);
        System.out.println("----------");
    }


    private static void returnFilmEarly() {
        System.out.println("RETURN FILM EARLY");
        System.out.println("----------");

        ReturnDTO returnDTO = new ReturnDTO(0, LocalDate.of(2017, 4, 14), Arrays.asList(0));

        controller.returnFilms(returnDTO);
        System.out.println("----------");
    }

    private static void returnFilmOnTime() {
        System.out.println("RETURN FILM ON TIME");
        System.out.println("----------");

        ReturnDTO returnDTO = new ReturnDTO(0, LocalDate.of(2017, 4, 15), Arrays.asList(1));

        controller.returnFilms(returnDTO);
        System.out.println("----------");
    }

    private static void returnFilmLate() {
        System.out.println("RETURN FILM LATE");
        System.out.println("----------");

        ReturnDTO returnDTO = new ReturnDTO(0, LocalDate.of(2017, 4, 17), Arrays.asList(4));

        controller.returnFilms(returnDTO);
        System.out.println("----------");
    }


    /**
     * As I have no import.sql, this will have to do.
     */
    private static void addSampleData() {
        List<Film> films = new ArrayList<>();
        films.add(new Film(0, "Film 0", FilmType.REGULAR));
        films.add(new Film(1, "Film 1", FilmType.NEW_RELEASE));
        films.add(new Film(2, "Film 2", FilmType.OLD));
        films.add(new Film(3, "Film 3", FilmType.REGULAR));
        films.add(new Film(4, "Film 4", FilmType.NEW_RELEASE));
        Repository.films.addAll(films);

        Customer c = new Customer(0);
        c.addPoints(30);
        Repository.customers.add(c);
        System.out.println("CREATED COSTUMER WITH 30 BONUS POINTS");
        System.out.println("----------");

    }
}
