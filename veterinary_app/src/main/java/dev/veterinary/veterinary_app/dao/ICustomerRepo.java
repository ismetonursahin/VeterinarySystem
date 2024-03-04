package dev.veterinary.veterinary_app.dao;

import dev.veterinary.veterinary_app.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.DefaultEditorKit;
import java.util.Optional;

public interface ICustomerRepo extends JpaRepository<Customer,Long> {

  Customer findByName(String name);

  Optional<Customer> findByNameAndMail(String name, String mail);

}
