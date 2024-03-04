package dev.veterinary.veterinary_app.dto.response.Doctor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.veterinary.veterinary_app.entities.Appointment;
import dev.veterinary.veterinary_app.entities.AvailableDate;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorResponse {

    private  Long id;
    private String name;
    private String phone;
    private String mail;
    private String address;
    private String city;
    private List<Appointment> appointmentList;
    private List<AvailableDate> availableDateList;

}
