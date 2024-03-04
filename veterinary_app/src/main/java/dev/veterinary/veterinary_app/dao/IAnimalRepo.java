package dev.veterinary.veterinary_app.dao;

import dev.veterinary.veterinary_app.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAnimalRepo extends JpaRepository<Animal,Long> {
    Animal findByName(String name);
}
