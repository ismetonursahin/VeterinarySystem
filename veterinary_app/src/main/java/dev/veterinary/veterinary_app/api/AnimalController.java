package dev.veterinary.veterinary_app.api;

import dev.veterinary.veterinary_app.business.abstracts.IAnimalService;
import dev.veterinary.veterinary_app.business.abstracts.ICustomerService;
import dev.veterinary.veterinary_app.core.config.modelMapper.IModelMapperService;
import dev.veterinary.veterinary_app.core.result.Result;
import dev.veterinary.veterinary_app.core.result.ResultData;
import dev.veterinary.veterinary_app.core.utilies.ResultHelper;
import dev.veterinary.veterinary_app.dto.CursorResponse;
import dev.veterinary.veterinary_app.dto.request.Animal.AnimalSaveRequest;
import dev.veterinary.veterinary_app.dto.request.Animal.AnimalUpdateRequest;
import dev.veterinary.veterinary_app.dto.request.Customer.CustomerSaveRequest;
import dev.veterinary.veterinary_app.dto.response.Animal.AnimalResponse;
import dev.veterinary.veterinary_app.dto.response.Customer.CustomerResponse;
import dev.veterinary.veterinary_app.dto.response.Vaccine.VaccineResponse;
import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Customer;
import dev.veterinary.veterinary_app.entities.Vaccine;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/animals")
public class AnimalController {

    @Autowired
    private final IAnimalService animalService;
    private final IModelMapperService modelMapper;
    private final ICustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AnimalResponse> save(@Valid @RequestBody AnimalSaveRequest animalSaveRequest){

        Customer customer = this.customerService.get(animalSaveRequest.getCustomer_id());
        animalSaveRequest.setCustomer_id(0L);

        Animal saveAnimals =this.modelMapper.forRequest().map(animalSaveRequest,Animal.class);
        saveAnimals.setCustomer(customer);

        this.animalService.save(saveAnimals);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAnimals,AnimalResponse.class));

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> get(@PathVariable("id") Long id){
        Animal animal = this.animalService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal,AnimalResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.animalService.delete(id);
        return ResultHelper.ok();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> update(@Valid @RequestBody AnimalUpdateRequest animalUpdateRequest){
        this.animalService.get(animalUpdateRequest.getId());
        Animal updateAnimal= this.modelMapper.forRequest().map(animalUpdateRequest, Animal.class);
        this.animalService.update(updateAnimal);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateAnimal, AnimalResponse.class));

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AnimalResponse>> cursor(
            @RequestParam(name = "page" , required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize" ,required = false,defaultValue = "5") int pageSize
    ){
        Page<Animal> customerPage = this.animalService.cursor(page,pageSize);
        Page<AnimalResponse> animalResponsePage = customerPage.map(animal ->
                this.modelMapper.forResponse().map(animal,AnimalResponse.class));
        return ResultHelper.cursor(animalResponsePage);
    }

    @GetMapping("/{id}/vaccines")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<List<VaccineResponse>> getAnimalVaccines(@PathVariable("id") Long animalId) {
        Animal animal = this.animalService.get(animalId);
        List<Vaccine> animalVaccines = animal.getVaccineList();
        List<VaccineResponse> vaccineResponses = animalVaccines.stream()
                .map(vaccine -> this.modelMapper.forResponse().map(vaccine, VaccineResponse.class))
                .collect(Collectors.toList());
        return ResultHelper.success(vaccineResponses);
    }


    @GetMapping("/getByName/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AnimalResponse> getByName(@PathVariable(name = "name") String name){
        Animal animal = this.animalService.getByName(name);
        return ResultHelper.success(this.modelMapper.forResponse().map(animal,AnimalResponse.class));
    }


}
