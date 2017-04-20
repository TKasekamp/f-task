package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.dto.returning.ReturnDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnReceiptDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnReceiptItemDTO;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.Rent;
import ee.tkasekamp.ftask.repository.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReturnService {

    private FilmService filmService;
    private RentService rentService;
    private List<Rent> rents;

    public ReturnService() {
        filmService = new FilmService();
        rents = Repository.rents;
        rentService = new RentService();
    }

    /**
     * For each item in dto:
     * Find match in rents. If return date is less exact or earlier,  cost 0.
     * Else, calculate with functions
     * Set film as available
     */
    public ReturnReceiptDTO returnFilms(ReturnDTO returnDTO) {
        List<ReturnReceiptItemDTO> items = new ArrayList<>();
        for (int filmID : returnDTO.getItems()) {
            items.add(returnFilm(returnDTO.getDate(), filmID));
        }

        long total = items.stream().mapToLong(ReturnReceiptItemDTO::getPrice).sum();
        return new ReturnReceiptDTO(total, items);
    }

    private ReturnReceiptItemDTO returnFilm(LocalDate returnDate, int filmID) {
        Film film = filmService.getFilm(filmID);
        filmService.setAvailable(filmID, true);

        Rent rent = rentService.getRent(filmID);

        int dateDifference = rentService.dateDifference(rent.getEndDate(), returnDate);

        int extraDays = 0;
        int price = 0;
        if (dateDifference > 0) {
            extraDays = dateDifference;
            price = rentService.calculatePrice(film, dateDifference);
        }
        return new ReturnReceiptItemDTO(film, extraDays, price);


    }


}
