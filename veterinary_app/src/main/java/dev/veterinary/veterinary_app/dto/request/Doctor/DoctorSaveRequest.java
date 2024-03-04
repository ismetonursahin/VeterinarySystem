package dev.veterinary.veterinary_app.dto.request.Doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Appointment;
import dev.veterinary.veterinary_app.entities.AvailableDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorSaveRequest {

    @NotNull(message = "Doktor Adı boş olamaz")
    private String name;
    @NotNull(message = "Doktor telefon giriniz")
    private String phone;
    @NotNull(message = "Doktor Mail Giriniz")
    private String mail;
    @NotNull(message = "Adres yazınız")
    private String address;
    @NotNull(message = "Şehiri yazınız")
    private String city;



}
