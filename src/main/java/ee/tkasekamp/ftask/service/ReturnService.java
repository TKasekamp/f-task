package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.dto.returning.ReturnDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnReceiptDTO;

public class ReturnService {

    private FilmService filmService;

    public ReturnService() {
        filmService = new FilmService();
    }

    public ReturnReceiptDTO returnFilms(ReturnDTO returnDTO) {

        return null;
    }
}
