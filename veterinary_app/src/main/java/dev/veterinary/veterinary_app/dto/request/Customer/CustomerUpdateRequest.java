package dev.veterinary.veterinary_app.dto.request.Customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.veterinary.veterinary_app.entities.Animal;
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
public class CustomerUpdateRequest {
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

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Animal> animalList;

}
