package dev.veterinary.veterinary_app.dto.response.Appointment;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Doctor;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponse {

    private Long id;
    private LocalDateTime appointmentDate;
    private Doctor doctor;
    private Animal animal;

}
