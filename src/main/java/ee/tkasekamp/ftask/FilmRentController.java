package ee.tkasekamp.ftask;

import ee.tkasekamp.ftask.dto.ReceiptDTO;
import ee.tkasekamp.ftask.dto.ReceiptItemDTO;
import ee.tkasekamp.ftask.dto.rent.CreateRentDTO;
import ee.tkasekamp.ftask.model.Customer;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;
import ee.tkasekamp.ftask.repository.Repository;
import ee.tkasekamp.ftask.service.RentService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tõnis Kasekamp on 12.04.2017.
 */
public class FilmRentController {

    RentService rentService;

    public FilmRentController() {
        this.rentService = new RentService();
    }

    public void rentFilms(CreateRentDTO dto) {
        ReceiptDTO receiptDTO = rentService.rentFilms(dto);

        printReceipt(receiptDTO);

    }

    public void printReceipt(ReceiptDTO dto) {
        System.out.println("Film rent receipt");

        dto.getItems().forEach(i -> System.out.println(i.toString()));

        System.out.println("Total price: "+ dto.getTotal() + " EUR");
        System.out.println("Your bonus points: "+ dto.getBonusPointsRemaining());
    }

}
