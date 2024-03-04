package dev.veterinary.veterinary_app.business.abstracts;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Appointment;
import dev.veterinary.veterinary_app.entities.Doctor;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IAppointmentService {
    Appointment save(Appointment appointment);

    Appointment get(Long id);

    Page<Appointment> cursor(int page, int pageSize);

    Appointment update(Appointment appointment);

    boolean delete(Long id);

    List<Appointment> getByDateAndDoctor(LocalDateTime startTime, LocalDateTime endTime, Doctor doctor);

    List<Appointment> getByDateTimeAndAnimal(LocalDateTime startTime, LocalDateTime endTime, Animal animal);
}
