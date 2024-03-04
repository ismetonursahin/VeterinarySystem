package dev.veterinary.veterinary_app.business.abstracts;

import dev.veterinary.veterinary_app.entities.Vaccine;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface IVaccineService {

    Vaccine save(Vaccine vaccine);

    Vaccine get(Long id);

    Page<Vaccine> cursor(int page, int pageSize);

    Vaccine update(Vaccine vaccine);

    boolean delete(Long id);

    List<Vaccine> findByDate(LocalDate start_date, LocalDate finish_date);
}
