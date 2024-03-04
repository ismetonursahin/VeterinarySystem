package dev.veterinary.veterinary_app.business.concretes;

import dev.veterinary.veterinary_app.business.abstracts.IDoctorService;
import dev.veterinary.veterinary_app.core.exception.NoSaveException;
import dev.veterinary.veterinary_app.core.exception.NotFoundException;
import dev.veterinary.veterinary_app.core.utilies.Msg;
import dev.veterinary.veterinary_app.dao.IDoctorRepo;
import dev.veterinary.veterinary_app.entities.Doctor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorManager implements IDoctorService {
    private final IDoctorRepo doctorRepo;

    @Override
    public Doctor save(Doctor doctor) {
        Optional<Doctor> doctorFromDb = this.doctorRepo.findByNameAndPhone(doctor.getName(),doctor.getPhone());
        if (doctorFromDb.isPresent()){
            throw new NoSaveException(doctorFromDb.get().getId(),Doctor.class);
        }
        return this.doctorRepo.save(doctor);
    }

    @Override
    public Doctor get(Long id) {
        return this.doctorRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<Doctor> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.doctorRepo.findAll(pageable);
    }

    @Override
    public Doctor update(Doctor doctor) {
        this.get(doctor.getId());
        return this.doctorRepo.save(doctor);
    }

    @Override
    public boolean delete(Long id) {
        Doctor doctor = this.get(id);
        this.doctorRepo.delete(doctor);
        return true;
    }
}
