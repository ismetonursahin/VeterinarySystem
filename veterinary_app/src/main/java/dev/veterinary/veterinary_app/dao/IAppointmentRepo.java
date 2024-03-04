package dev.veterinary.veterinary_app.dao;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Appointment;
import dev.veterinary.veterinary_app.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentRepo extends JpaRepository<Appointment,Long> {

    List<Appointment> findByAppointmentDateBetweenAndDoctor(LocalDateTime startTime, LocalDateTime endTime, Doctor doctor);

    List<Appointment> findByAppointmentDateBetweenAndAnimal(LocalDateTime startTime, LocalDateTime endTime, Animal animal);


}
