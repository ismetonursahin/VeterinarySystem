package dev.veterinary.veterinary_app.dao;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Appointment;
import dev.veterinary.veterinary_app.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IAppointmentRepo extends JpaRepository<Appointment,Long> {

    Optional<Appointment> findByAppointmentDateAndAnimalAndDoctor(LocalDateTime appointmentDate, Animal animal, Doctor doctor);

    List<Appointment> findByAppointmentDateBetweenAndDoctor(LocalDateTime startTime, LocalDateTime endTime, Doctor doctor);

    List<Appointment> findByAppointmentDateBetweenAndAnimal(LocalDateTime startTime, LocalDateTime endTime, Animal animal);

    Optional<Appointment> findByDoctorAndAppointmentDateBetween(Doctor doctor, LocalDateTime startTime, LocalDateTime endTime);
}
