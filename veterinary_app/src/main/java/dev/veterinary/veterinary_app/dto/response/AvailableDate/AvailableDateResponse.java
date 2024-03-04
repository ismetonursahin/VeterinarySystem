package dev.veterinary.veterinary_app.dto.response.AvailableDate;

import dev.veterinary.veterinary_app.entities.Doctor;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateResponse {

    private Long id;

    private LocalDate availableDate;

    private Doctor doctor;
}
