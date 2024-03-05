package dev.veterinary.veterinary_app.business.concretes;

import dev.veterinary.veterinary_app.business.abstracts.IAppointmentService;
import dev.veterinary.veterinary_app.business.abstracts.IDoctorService;
import dev.veterinary.veterinary_app.core.exception.NoSaveException;
import dev.veterinary.veterinary_app.core.exception.NotFoundException;
import dev.veterinary.veterinary_app.core.utilies.Msg;
import dev.veterinary.veterinary_app.dao.IAppointmentRepo;
import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Appointment;
import dev.veterinary.veterinary_app.entities.AvailableDate;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentManager implements IAppointmentService {
    private final IAppointmentRepo appointmentRepo;
    private final IDoctorService doctorService;



    @Override
    public Appointment save(Appointment appointment) {
        Optional<Appointment> appointmentFromDb = this.appointmentRepo.findByAppointmentDateAndAnimalAndDoctor(appointment.getAppointmentDate(), appointment.getAnimal(), appointment.getDoctor());
        if (appointmentFromDb.isPresent()) {
            throw new NoSaveException(appointmentFromDb.get().getId(), Appointment.class);
        }
        // Değerlendirme Formu 22
        List<LocalDate> availableDates = appointment.getDoctor().getAvailableDateList().stream()
                .map(AvailableDate::getAvailableDate)
                .collect(Collectors.toList());
        LocalDate appointmentDate = appointment.getAppointmentDate().toLocalDate();
        if (availableDates.contains(appointmentDate)) {
            if (appointment.getAppointmentDate().getMinute() == 0 && appointment.getAppointmentDate().getSecond() == 0) {
                LocalDateTime endDateTime = appointment.getAppointmentDate().plusHours(1);
                Optional<Appointment> existingAppointment = this.appointmentRepo.findByDoctorAndAppointmentDateBetween(appointment.getDoctor(), appointment.getAppointmentDate(), endDateTime);

                if (existingAppointment.isPresent()) {
                    throw new NotFoundException("Bu saat için uygun randevu bulunmamaktadır");
                } else {
                    return this.appointmentRepo.save(appointment);
                }
            } else {
                throw new NotFoundException("Sadece tam saatlerde randevu alınabilir.");
            }
        } else {
            throw new NotFoundException("Doktor bu tarihte çalışmamaktadır");
        }
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
