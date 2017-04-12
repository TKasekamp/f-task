package ee.tkasekamp.ftask;

import ee.tkasekamp.ftask.dto.rent.CreateRentDTO;
import ee.tkasekamp.ftask.service.RentService;

/**
 * Created by Tõnis Kasekamp on 12.04.2017.
 */
public class RentController {

    RentService rentService;

    public RentController() {
        this.rentService = new RentService();
    }

    public void rentFilms(CreateRentDTO dto) {


    }


}
