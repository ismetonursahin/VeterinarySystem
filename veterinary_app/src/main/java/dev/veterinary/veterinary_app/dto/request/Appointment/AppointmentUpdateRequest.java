package dev.veterinary.veterinary_app.dto.request.Appointment;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Doctor;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentUpdateRequest {
    @Positive(message = "ID Boş Bırakılamaz")
    private Long id;
    @NotNull(message = "Tarih Boş olamaz.")
    private LocalDateTime appointmentDate;
    @NotNull(message = "Doktor alanı boş olamaz")
    private Doctor doctor;
    @NotNull(message = "Hayvan Boş olamaz")
    private Animal animal;

}
