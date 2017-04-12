package ee.tkasekamp.ftask.service;

import ee.tkasekamp.ftask.model.Customer;
import ee.tkasekamp.ftask.repository.Repository;

import java.util.List;

public class CostumerService {
    List<Customer> customers;

    public CostumerService() {
        this.customers = Repository.customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public int getBonusPoints(int id) {
        return getCustomer(id).getBonusPoints();
    }

    public Customer getCustomer(int id) {
        return customers.stream().filter(x -> id == x.getID()).findFirst().get();
    }

    public void addPoints(int id, int points) {
        customers.stream().filter(x -> id == x.getID()).findFirst().get().addPoints(points);
    }

    public void removePoints(int id, int points) {
        customers.stream().filter(x -> id == x.getID()).findFirst().get().removePoints(points);
    }
}
