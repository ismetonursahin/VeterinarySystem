package dev.veterinary.veterinary_app.business.concretes;

import dev.veterinary.veterinary_app.business.abstracts.IAnimalService;
import dev.veterinary.veterinary_app.core.exception.NotFoundException;
import dev.veterinary.veterinary_app.core.utilies.Msg;
import dev.veterinary.veterinary_app.dao.IAnimalRepo;
import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Vaccine;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalManager implements IAnimalService {

    private final IAnimalRepo animalRepo;

    @Override
    public Animal save(Animal animal) {
        return this.animalRepo.save(animal);
    }

    @Override
    public Animal get(Long id) {
        return this.animalRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Animal> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.animalRepo.findAll(pageable);
    }

    @Override
    public Animal update(Animal animal) {
        this.get(animal.getId());
        return this.animalRepo.save(animal);
    }

    @Override
    public boolean delete(Long id) {
        Animal animal = this.get(id);
        this.animalRepo.delete(animal);
        return true;
    }

    @Override
    public Animal getByName(String name) {
        return this.animalRepo.findByName(name);
    }

    @Override
    public List<Vaccine> getAnimalVaccines(Long animalId) {
        Animal animal = this.get(animalId);
        return animal.getVaccineList();
    }

}
