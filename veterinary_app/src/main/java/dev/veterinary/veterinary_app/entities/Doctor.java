package dev.veterinary.veterinary_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jdk.internal.misc.CarrierThreadLocal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Doctors")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , columnDefinition = "serial")
    private  Long id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "phone" , nullable = false)
    private String phone;

    @Column(name = "mail" , nullable = false)
    private String mail;

    @Column(name = "address" , nullable = false)
    private String address;

    @Column(name = "city" , nullable = false)
    private String city;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointmentList;

    @OneToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<AvailableDate> availableDateList;


}
