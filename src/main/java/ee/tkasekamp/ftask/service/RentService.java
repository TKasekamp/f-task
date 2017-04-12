package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.dto.ReceiptDTO;
import ee.tkasekamp.ftask.dto.ReceiptItemDTO;
import ee.tkasekamp.ftask.dto.rent.CreateRentDTO;
import ee.tkasekamp.ftask.dto.rent.RentItemDTO;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;
import ee.tkasekamp.ftask.model.Rent;
import ee.tkasekamp.ftask.repository.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.toIntExact;

import static java.time.temporal.ChronoUnit.DAYS;

public class RentService {
    private static final int NEW_RELEASE_BONUS_COST = 25;
    public final int BASIC_PRICE = 3;
    public final int PREMIUM_PRICE = 4;

    public final int REGULAR_PRICE_DAYS = 3;
    public final int OLD_PRICE_DAYS = 5;

    public final int NEW_RELEASE_BONUS_POINTS = 2;
    public final int OTHER_BONUS_POINTS = 1;

    private CostumerService costumerService;
    private FilmService filmService;
    private List<Rent> rents;

    public RentService() {
        costumerService = new CostumerService();
        filmService = new FilmService();
        this.rents = Repository.rents;
    }

    public ReceiptDTO rentFilms(CreateRentDTO dto) {
        List<ReceiptItemDTO> items = new ArrayList<>();
        for (RentItemDTO rentItemDTO : dto.getItems()) {
            items.add(rentFilm(dto.getCostumerID(), rentItemDTO));
        }
        // Calculate total
        long total = items.stream().mapToLong(ReceiptItemDTO::getPrice).sum();
        // Calculate bonuses if needed.
        boolean usedBonus = items.stream()
                .anyMatch(t -> t.usedBonus());

        // Find remaining bonus points
        int bonusPointsRemaining = costumerService.getBonusPoints(dto.getCostumerID());
        return new ReceiptDTO(total, usedBonus, bonusPointsRemaining, items);
    }

    public ReceiptItemDTO rentFilm(int costumerID, RentItemDTO dto) {
        Film film = filmService.getFilm(dto.getFilmID());
        filmService.setAvailable(dto.getFilmID(), false);
        addBonusPoints(costumerID, film.getType());


        int days = dateDifference(dto.getStartDate(), dto.getEndDate());
        if(dto.isPayWithBonus())
            days = payWithBonus(costumerID, days);


        int price = calculatePrice(film, days);

        Rent rent = new Rent(dto.getFilmID(),costumerID,  dto.getStartDate(), dto.getEndDate(), price, dto.isPayWithBonus());

        rents.add(rent);

        return new ReceiptItemDTO(film, days, price, dto.isPayWithBonus());
    }

    public boolean checkBonusAvailability(int costumerID, FilmType type) {
        return false;
    }

    private void addBonusPoints(int costumerID, FilmType type) {
        if (type == FilmType.NEW_RELEASE) {
            costumerService.addPoints(costumerID, NEW_RELEASE_BONUS_POINTS);
        } else {
            costumerService.addPoints(costumerID, OTHER_BONUS_POINTS);
        }
    }

    private int payWithBonus(int costumerId, int days) {
        int bonusPoints = costumerService.getBonusPoints(costumerId);
        int daysCoveredByBonus = bonusPoints / NEW_RELEASE_BONUS_COST;

        costumerService.removePoints(costumerId, daysCoveredByBonus * NEW_RELEASE_BONUS_COST);

        days -= daysCoveredByBonus;

        return days;
    }

    public int calculatePrice(Film film, int days) {
        FilmType type = film.getType();
        int price = 0;
        switch (type) {
            case NEW_RELEASE:
                price = PREMIUM_PRICE * days;
                break;
            case REGULAR:
                price = calculateRegularPrice(days);
                break;
            case OLD:
                price = calculateOldPrice(days);
                break;

        }
        return price;

    }

    private int calculateRegularPrice(int days) {
        int price = BASIC_PRICE;

        int overDays = days - REGULAR_PRICE_DAYS;

        if (overDays > 0) {
            price += overDays * BASIC_PRICE;
        }

        return price;
    }

    private int calculateOldPrice(int days) {
        int price = BASIC_PRICE;

        int overDays = days - OLD_PRICE_DAYS;

        if (overDays > 0) {
            price += overDays * BASIC_PRICE;
        }

        return price;
    }

    private int dateDifference(LocalDate startDate, LocalDate endDate) {
        return toIntExact(DAYS.between(startDate, endDate));
    }
}
