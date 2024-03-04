package dev.veterinary.veterinary_app.dto.request.Animal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.veterinary.veterinary_app.entities.Appointment;
import dev.veterinary.veterinary_app.entities.Customer;
import dev.veterinary.veterinary_app.entities.Vaccine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalSaveRequest {

    @NotNull(message = "İsim Boş Olamaz")
    private String name;

    @NotNull(message = "Tür Boş Bırakılamaz")
    private String species;

    @NotNull(message = "Yavru Alanı Boş Bırakılamaz")
    private String breed;

    @NotNull(message = "Cinsiyet Giriniz")
    private String gender;

    @NotNull(message = "Hayvan Rengi")
    private String colour;

    @NotNull(message = "Doğum Tarihi Giriniz")
    private LocalDate dateOfBirth;

    @NotNull(message = "Id Giriniz")
    private Long customer_id;

}
