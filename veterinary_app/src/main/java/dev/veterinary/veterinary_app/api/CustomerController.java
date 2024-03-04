package dev.veterinary.veterinary_app.api;

import dev.veterinary.veterinary_app.business.abstracts.ICustomerService;
import dev.veterinary.veterinary_app.core.config.modelMapper.IModelMapperService;
import dev.veterinary.veterinary_app.core.result.Result;
import dev.veterinary.veterinary_app.core.result.ResultData;
import dev.veterinary.veterinary_app.core.utilies.Msg;
import dev.veterinary.veterinary_app.core.utilies.ResultHelper;
import dev.veterinary.veterinary_app.dto.CursorResponse;
import dev.veterinary.veterinary_app.dto.request.Customer.CustomerSaveRequest;
import dev.veterinary.veterinary_app.dto.request.Customer.CustomerUpdateRequest;
import dev.veterinary.veterinary_app.dto.response.Animal.AnimalResponse;
import dev.veterinary.veterinary_app.dto.response.Customer.CustomerResponse;
import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Customer;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final ICustomerService customerService;
    private final IModelMapperService modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<CustomerResponse> save(@Valid @RequestBody CustomerSaveRequest customerSaveRequest) {
        Customer saveCustomer = this.modelMapper.forRequest().map(customerSaveRequest, Customer.class);
        this.customerService.save(saveCustomer);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveCustomer, CustomerResponse.class));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> get(@PathVariable(name = "id") Long id) {
        Customer customer = this.customerService.get(id);
        //  customer.getAnimalList().stream().map(animal ->)
        return ResultHelper.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id) {
        this.customerService.delete(id);
        return ResultHelper.ok();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> update(@Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        this.customerService.get(customerUpdateRequest.getId());
        Customer updateCustomer = this.modelMapper.forRequest().map(customerUpdateRequest, Customer.class);
        this.customerService.update(updateCustomer);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateCustomer, CustomerResponse.class));

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<CustomerResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize
    ) {
        Page<Customer> customerPage = this.customerService.cursor(page, pageSize);
        Page<CustomerResponse> customerResponsePage = customerPage.map(customer ->
                this.modelMapper.forResponse().map(customer, CustomerResponse.class));
        return ResultHelper.cursor(customerResponsePage);
    }


    @GetMapping("/{id}/animals")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<AnimalResponse>> getCustomerAnimals(@PathVariable("id") Long customerId) {
        Customer customer = this.customerService.get(customerId);
        List<Animal> customerAnimals = customer.getAnimalList();
        List<AnimalResponse> animalResponses = customerAnimals.stream()
                .map(animal -> this.modelMapper.forResponse().map(animal, AnimalResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(animalResponses);
    }


    @GetMapping("/getByName/{name}")  //  farklı isimde sorgulanan kullanıcı error sor?
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CustomerResponse> getByName(@PathVariable("name") String name) {
        Customer customer = this.customerService.getByName(name);
        return ResultHelper.success(this.modelMapper.forResponse().map(customer, CustomerResponse.class));
    }


}


