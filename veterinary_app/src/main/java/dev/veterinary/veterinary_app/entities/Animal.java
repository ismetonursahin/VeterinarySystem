package dev.veterinary.veterinary_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Animals")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" ,columnDefinition = "serial")
    private Long id;

    @Column(name = "name" , nullable = false)
    private String name;

    @Column(name = "species" , nullable = false)
    private String species;

    @Column(name = "breed" , nullable = false)
    private String breed;

    @Column(name = "gender" ,nullable = false)
    private String gender;

    @Column(name = "colour" ,nullable = false)
    private String colour;

    @JoinColumn(name = "dateOfBirth" , nullable = false)
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "customer_id" , referencedColumnName = "id")
    private Customer customer;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vaccine> vaccineList;

    @OneToMany(mappedBy = "animal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Appointment> appointmentList;


}
