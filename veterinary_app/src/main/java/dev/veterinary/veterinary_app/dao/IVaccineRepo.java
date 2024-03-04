package dev.veterinary.veterinary_app.dao;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Vaccine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineRepo extends JpaRepository<Vaccine,Long> {

    List<Vaccine> findByProtectionFinishDateBetween(LocalDate start_date, LocalDate finish_date);
}
