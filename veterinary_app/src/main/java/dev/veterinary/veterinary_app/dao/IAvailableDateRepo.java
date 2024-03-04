package dev.veterinary.veterinary_app.dao;

import dev.veterinary.veterinary_app.entities.AvailableDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAvailableDateRepo extends JpaRepository<AvailableDate,Long> {


}
