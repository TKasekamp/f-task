package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FilmServiceTest {

    FilmService filmService;

    @Before
    public void setup() {
        filmService = new FilmService();

        List<Film> films = new ArrayList<>();
        films.add(new Film(0, "Film 0", FilmType.REGULAR));
        films.add(new Film(1, "Film 1", FilmType.NEW_RELEASE));
        films.add(new Film(2, "Film 2", FilmType.OLD));
        films.add(new Film(3, "Film 3", FilmType.REGULAR));
        films.add(new Film(4, "Film 4", FilmType.REGULAR));
        filmService.setFilms(films);
    }

    @Test
    public void findTest() {
        Film film = filmService.getFilm(2);
        Assert.assertEquals(2, film.getID());
    }

    @Test
    public void addTest() {
        Film film = new Film(5, "Film 5", FilmType.NEW_RELEASE);
        filmService.addFilm(film);
        Assert.assertEquals(6, filmService.getFilms().size());
    }

    @Test
    public void removeTest() {
        filmService.removeFilm(3);
        Assert.assertEquals(4, filmService.getFilms().size());
    }

    @Test
    public void changeTypeTest() {
        filmService.changeFilmType(2, FilmType.REGULAR);
        Film film = filmService.getFilm(2);
        Assert.assertEquals(FilmType.REGULAR, film.getType());
    }

    @Test
    public void changeAvailableTest() {
        filmService.setAvailable(2, false);
        Film film = filmService.getFilm(2);
        Assert.assertFalse(film.isAvailable());
    }

    @Test
    public void findAllAvailable() {
        filmService.setAvailable(2, false);
        filmService.setAvailable(3, false);
        Assert.assertEquals(3, filmService.getAvailableFilms().size());
    }


}
