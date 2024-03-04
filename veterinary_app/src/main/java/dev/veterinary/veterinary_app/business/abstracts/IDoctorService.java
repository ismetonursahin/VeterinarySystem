package dev.veterinary.veterinary_app.business.abstracts;

import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Doctor;
import org.springframework.data.domain.Page;

public interface IDoctorService {
    Doctor save(Doctor doctor);

    Doctor get(Long id);

    Page<Doctor> cursor(int page, int pageSize);

    Doctor update(Doctor doctor);

    boolean delete(Long id);
}
