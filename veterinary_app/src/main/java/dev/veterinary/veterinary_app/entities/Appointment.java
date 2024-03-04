package dev.veterinary.veterinary_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "Appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , columnDefinition = "serial")
    private Long id;

    @JoinColumn(name ="appointment_date")
    private LocalDateTime appointmentDate;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;
}
