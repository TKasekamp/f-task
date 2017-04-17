package ee.tkasekamp.ftask;

import ee.tkasekamp.ftask.dto.ReceiptDTO;
import ee.tkasekamp.ftask.dto.rent.CreateRentDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnDTO;
import ee.tkasekamp.ftask.dto.returning.ReturnReceiptDTO;
import ee.tkasekamp.ftask.service.RentService;
import ee.tkasekamp.ftask.service.ReturnService;

/**
 * Created by Tõnis Kasekamp on 12.04.2017.
 */
public class FilmRentController {

    RentService rentService;
    ReturnService returnService;

    public FilmRentController() {
        this.rentService = new RentService();
        this.returnService = new ReturnService();
    }

    public void rentFilms(CreateRentDTO dto) {
        ReceiptDTO receiptDTO = rentService.rentFilms(dto);

        printReceipt(receiptDTO);

    }

    public void returnFilms(ReturnDTO returnDTO) {
        ReturnReceiptDTO returnReceiptDTO = returnService.returnFilms(returnDTO);

        printReturnReceipt(returnReceiptDTO);

    }

    public void printReceipt(ReceiptDTO dto) {
        System.out.println("Film rent receipt");

        dto.getItems().forEach(i -> System.out.println(i.toString()));

        System.out.println("Total price: " + dto.getTotal() + " EUR");
        System.out.println("Your bonus points: " + dto.getBonusPointsRemaining());
    }

    public void printReturnReceipt(ReturnReceiptDTO dto) {
        System.out.println("Film return receipt");

        dto.getItems().forEach(i -> System.out.println(i.toString()));

        System.out.println("Total late charge: " + dto.getTotal() + " EUR");
    }

}
