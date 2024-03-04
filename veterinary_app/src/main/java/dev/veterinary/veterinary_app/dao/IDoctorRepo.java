package dev.veterinary.veterinary_app.dao;

import dev.veterinary.veterinary_app.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IDoctorRepo extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByNameAndPhone(String name, String phone);
}
