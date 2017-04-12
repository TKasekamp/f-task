package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FilmService {
    List<Film> films;

    public FilmService() {
        films = new ArrayList<>();
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public List<Film> getAvailableFilms() {
        return films.stream().filter(film -> film.isAvailable()).collect(Collectors.toList());
    }

    public void setAvailable(int id, boolean available) {
        films.stream().filter(x -> id == x.getID()).findFirst().get().setAvailable(available);
    }

    public void addFilm(Film film) {
        films.add(film);
    }

    public void removeFilm(int id) {
        films.removeIf(p -> p.getID() == id);
    }

    public void changeFilmType(int id, FilmType newType) {
        films.stream().filter(x -> id == x.getID()).findFirst().get().setType(newType);
    }

    public Film getFilm(int id) {
        return films.stream().filter(x -> id == x.getID()).findFirst().get();
    }
}
