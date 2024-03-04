package dev.veterinary.veterinary_app.business.abstracts;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.util.List;


public interface IAnimalService {

    Animal save(Animal animal);

    Animal get(Long id);

    Page<Animal> cursor(int page, int pageSize);

    Animal update(Animal animal);

    boolean delete(Long id);

    Animal getByName(String name);

    List<Vaccine> getAnimalVaccines(Long animalId);
}
