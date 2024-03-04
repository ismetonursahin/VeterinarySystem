package dev.veterinary.veterinary_app.dto.request.Vaccine;

import dev.veterinary.veterinary_app.entities.Animal;
import jakarta.persistence.Column;
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
public class VaccineSaveRequest {

    private String name;

    private String code;

    private LocalDate protectionStartDate;

    private LocalDate protectionFinishDate;

    private Long animal_id;

}
