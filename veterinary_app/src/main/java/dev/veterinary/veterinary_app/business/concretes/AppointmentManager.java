package dev.veterinary.veterinary_app.business.concretes;

import dev.veterinary.veterinary_app.business.abstracts.IAppointmentService;
import dev.veterinary.veterinary_app.business.abstracts.IDoctorService;
import dev.veterinary.veterinary_app.core.exception.NotFoundException;
import dev.veterinary.veterinary_app.core.utilies.Msg;
import dev.veterinary.veterinary_app.dao.IAppointmentRepo;
import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Appointment;
import dev.veterinary.veterinary_app.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {
    private final IAppointmentRepo appointmentRepo;
    private final IDoctorService doctorService;


    @Override
    public Appointment save(Appointment appointment) {
        return this.appointmentRepo.save(appointment);
    }


    @Override
    public Appointment get(Long id) {
        return this.appointmentRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Appointment> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.appointmentRepo.findAll(pageable);
    }

    @Override
    public Appointment update(Appointment appointment) {
        this.get(appointment.getId());
        return this.appointmentRepo.save(appointment);
    }

    @Override
    public boolean delete(Long id) {
        Appointment appointment = this.get(id);
        this.appointmentRepo.delete(appointment);
        return true;
    }


    @Override
    public List<Appointment> getByDateAndDoctor(LocalDateTime startTime, LocalDateTime endTime, Doctor doctor) {
        return this.appointmentRepo.findByAppointmentDateBetweenAndDoctor(startTime,endTime,doctor);
    }


    @Override
    public List<Appointment> getByDateTimeAndAnimal(LocalDateTime startTime, LocalDateTime endTime, Animal animal) {
        return this.appointmentRepo.findByAppointmentDateBetweenAndAnimal(startTime,endTime,animal);
    }


}
