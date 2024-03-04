package dev.veterinary.veterinary_app.dto.request.AvailableDate;

import dev.veterinary.veterinary_app.entities.Doctor;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateUpdateRequest {
    @Positive(message = "Id Boş Olamaz")
    private Long id;
    @NotNull(message = "Tarih giriniz")
    private LocalDate availableDate;
    @NotNull(message = "Doktor ID Değeri giriniz.")
    private Long doctor_id;
}
