package dev.veterinary.veterinary_app.business.abstracts;

import dev.veterinary.veterinary_app.entities.AvailableDate;
import org.springframework.data.domain.Page;

public interface IAvailableDateService {
    AvailableDate save(AvailableDate availableDate);

    AvailableDate get(Long id);

    Page<AvailableDate> cursor(int page, int pageSize);

    AvailableDate update(AvailableDate availableDate);

    boolean delete(Long id);
}
