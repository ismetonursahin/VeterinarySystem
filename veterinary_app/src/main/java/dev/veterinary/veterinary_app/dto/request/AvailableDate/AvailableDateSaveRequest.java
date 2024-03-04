package dev.veterinary.veterinary_app.dto.request.AvailableDate;

import dev.veterinary.veterinary_app.entities.Doctor;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDateSaveRequest {

    @NotNull(message = "Boş Bırakmayınız")
    private LocalDate availableDate;
    @NotNull(message = "Boş Bırakmayınız")
    private Long doctor_id;

}
