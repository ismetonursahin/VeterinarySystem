package dev.veterinary.veterinary_app.dto.response.Animal;

import dev.veterinary.veterinary_app.entities.Appointment;
import dev.veterinary.veterinary_app.entities.Customer;
import dev.veterinary.veterinary_app.entities.Vaccine;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalResponse {

    private Long id;
    private String name;
    private String species;
    private String breed;
    private String gender;
    private String colour;
    private LocalDate dateOfBirth;
    private Customer customer;

}
