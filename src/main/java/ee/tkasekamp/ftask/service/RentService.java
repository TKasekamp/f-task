package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.dto.ReceiptDTO;
import ee.tkasekamp.ftask.dto.ReceiptItemDTO;
import ee.tkasekamp.ftask.dto.rent.CreateRentDTO;
import ee.tkasekamp.ftask.dto.rent.RentItemDTO;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.FilmType;
import ee.tkasekamp.ftask.model.Rent;
import ee.tkasekamp.ftask.repository.Repository;
import ee.tkasekamp.ftask.service.dto.BonusCoverage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.toIntExact;
import static java.time.temporal.ChronoUnit.DAYS;

public class RentService {
    private static final int NEW_RELEASE_BONUS_COST = 25;
    private static final int BASIC_PRICE = 3;
    private static final int PREMIUM_PRICE = 4;

    private static final int REGULAR_PRICE_DAYS = 3;
    private static final int OLD_PRICE_DAYS = 5;

    private static final int NEW_RELEASE_BONUS_POINTS = 2;
    private static final int OTHER_BONUS_POINTS = 1;

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

        // Find remaining bonus points
        int bonusPointsRemaining = costumerService.getBonusPoints(dto.getCostumerID());
        return new ReceiptDTO(total, bonusPointsRemaining, items);
    }

    /**
     * Find the film from repo and add the bonus points for that. Then calculate the price of the rent using bonus points.
     *
     * @param costumerID Person who rents
     * @param dto        rent data
     * @return Receipt of this rent
     */
    public ReceiptItemDTO rentFilm(int costumerID, RentItemDTO dto) {
        Film film = filmService.getFilm(dto.getFilmID());
        filmService.setAvailable(dto.getFilmID(), false);
        addBonusPoints(costumerID, film.getType());

        int payableDays = dateDifference(dto.getStartDate(), dto.getEndDate());
        int bonusPointsUsed = 0;

        // If the costumer wants to use bonus points, change the day and bonus values
        if (dto.isPayWithBonus()) {
            BonusCoverage bonus = payWithBonus(costumerID, payableDays);
            payableDays = bonus.getPayableDays();
            bonusPointsUsed = bonus.getBonusPointsUsed();
        }

        int price = calculatePrice(film, payableDays);

        Rent rent = new Rent(dto.getFilmID(), dto.getEndDate());
        rents.add(rent);

        return new ReceiptItemDTO(film, payableDays, price, bonusPointsUsed);
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

    public Rent getRent(int filmID) {
        return rents.stream().filter(x -> filmID == x.getFilmID()).findFirst().get();
    }


    private void addBonusPoints(int costumerID, FilmType type) {
        if (type == FilmType.NEW_RELEASE) {
            costumerService.addPoints(costumerID, NEW_RELEASE_BONUS_POINTS);
        } else {
            costumerService.addPoints(costumerID, OTHER_BONUS_POINTS);
        }
    }

    private BonusCoverage payWithBonus(int costumerId, int days) {
        int bonusPoints = costumerService.getBonusPoints(costumerId);
        int daysCoveredByBonus = bonusPoints / NEW_RELEASE_BONUS_COST;

        int bonusPointsUsed = daysCoveredByBonus * NEW_RELEASE_BONUS_COST;
        costumerService.removePoints(costumerId, bonusPointsUsed);

        return new BonusCoverage(bonusPointsUsed, days - daysCoveredByBonus);
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

    public int dateDifference(LocalDate startDate, LocalDate endDate) {
        return toIntExact(DAYS.between(startDate, endDate));
    }
}
