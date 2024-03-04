package dev.veterinary.veterinary_app.dto.request.Appointment;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Doctor;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentSaveRequest {

    @NotNull(message = "Tarih Boş Bırakılamaz")
    private LocalDateTime appointmentDate;
    @NotNull(message = "Doktor ID Boş Bırakılamaz")
    private Long doctor_id;
    @NotNull(message = "Hayvan ID Boş Bırakılamaz")
    private Long animal_id;

}
