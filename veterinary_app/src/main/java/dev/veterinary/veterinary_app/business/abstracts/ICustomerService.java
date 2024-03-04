package dev.veterinary.veterinary_app.business.abstracts;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Customer;
import org.springframework.data.domain.Page;

import java.util.Currency;
import java.util.List;

public interface ICustomerService {
    Customer save(Customer customer);

    Customer get(Long id);

    Page<Customer> cursor(int page, int pageSize);

    Customer update(Customer customer);

    boolean delete(Long id);

    List<Animal> getCustomerAnimals(Long customerId);

    Customer getByName(String name);
}
