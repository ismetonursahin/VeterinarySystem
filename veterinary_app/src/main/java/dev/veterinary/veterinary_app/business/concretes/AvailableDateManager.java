package dev.veterinary.veterinary_app.business.concretes;

import dev.veterinary.veterinary_app.business.abstracts.IAvailableDateService;
import dev.veterinary.veterinary_app.core.exception.NotFoundException;
import dev.veterinary.veterinary_app.core.utilies.Msg;
import dev.veterinary.veterinary_app.dao.IAvailableDateRepo;
import dev.veterinary.veterinary_app.entities.AvailableDate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AvailableDateManager implements IAvailableDateService {
    private final IAvailableDateRepo availableDateRepo;

    @Override
    public AvailableDate save(AvailableDate availableDate) {
        return this.availableDateRepo.save(availableDate);
    }

    @Override
    public AvailableDate get(Long id) {
        return this.availableDateRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }

    @Override
    public Page<AvailableDate> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.availableDateRepo.findAll(pageable);
    }

    @Override
    public AvailableDate update(AvailableDate availableDate) {
        this.get(availableDate.getId());
        return null;
    }


    @Override
    public boolean delete(Long id) {
        AvailableDate availableDate = this.get(id);
        this.availableDateRepo.delete(availableDate);
        return true;
    }

}
