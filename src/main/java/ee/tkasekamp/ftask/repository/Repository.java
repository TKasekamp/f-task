package ee.tkasekamp.ftask.repository;

import ee.tkasekamp.ftask.model.Customer;
import ee.tkasekamp.ftask.model.Film;
import ee.tkasekamp.ftask.model.Rent;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    public static List<Film> films = new ArrayList<>();
    public static List<Customer> customers = new ArrayList<>();
    public static List<Rent> rents = new ArrayList<>();
}
