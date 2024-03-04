package dev.veterinary.veterinary_app.business.concretes;

import dev.veterinary.veterinary_app.business.abstracts.ICustomerService;
import dev.veterinary.veterinary_app.core.exception.NoSaveException;
import dev.veterinary.veterinary_app.core.exception.NotFoundException;
import dev.veterinary.veterinary_app.core.utilies.Msg;
import dev.veterinary.veterinary_app.dao.ICustomerRepo;
import dev.veterinary.veterinary_app.dto.response.Animal.AnimalResponse;
import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Customer;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerManager implements ICustomerService {
    private final ICustomerRepo customerRepo;

    @Override
    public Customer save(Customer customer) {
        Optional<Customer> customerFromDb = this.customerRepo.findByNameAndMail(customer.getName(),customer.getMail());
        if (customerFromDb.isPresent()){
            throw new NoSaveException(customerFromDb.get().getId(),Customer.class);
        }
        return this.customerRepo.save(customer);
    }

    @Override
    public Customer get(Long id) {
        return this.customerRepo.findById(id).orElseThrow(()-> new NotFoundException(Msg.NOT_FOUND));
    }


    @Override
    public Page<Customer> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page,pageSize);
        return this.customerRepo.findAll(pageable);
    }

    @Override
    public Customer update(Customer customer) {
        this.get(customer.getId());
        return this.customerRepo.save(customer);
    }

    @Override
    public boolean delete(Long id) {
        Customer customer = this.get(id);
        this.customerRepo.delete(customer);
        return true;
    }

    @Override
    public List<Animal> getCustomerAnimals(Long customerId) {
        Customer customer = this.get(customerId);
        return customer.getAnimalList();
    }

    @Override
    public Customer getByName(String name) {
         Customer customer = this.customerRepo.findByName(name);
         if(customer == null){
             throw new NotFoundException(name + " Kullanıcı bulunamadı.");
         }
         return customer;
    }


}
