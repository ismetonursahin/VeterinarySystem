package dev.veterinary.veterinary_app.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "AvailableDates")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvailableDate {
    @Id
    @Column(name = "id" , columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "available_date")
    private LocalDate availableDate;

    @ManyToOne (fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

}
