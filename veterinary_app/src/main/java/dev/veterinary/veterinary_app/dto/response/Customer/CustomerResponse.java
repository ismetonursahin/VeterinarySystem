package dev.veterinary.veterinary_app.dto.response.Customer;

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
public class CustomerResponse {

    private  Long id;

    private String name;

    private String phone;

    private String mail;

    private String address;

    private String city;

}
