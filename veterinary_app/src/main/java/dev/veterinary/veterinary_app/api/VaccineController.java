package dev.veterinary.veterinary_app.api;

import dev.veterinary.veterinary_app.business.abstracts.IAnimalService;
import dev.veterinary.veterinary_app.business.abstracts.IVaccineService;
import dev.veterinary.veterinary_app.core.config.modelMapper.IModelMapperService;
import dev.veterinary.veterinary_app.core.result.Result;
import dev.veterinary.veterinary_app.core.result.ResultData;
import dev.veterinary.veterinary_app.core.utilies.ResultHelper;
import dev.veterinary.veterinary_app.dao.IVaccineRepo;
import dev.veterinary.veterinary_app.dto.CursorResponse;
import dev.veterinary.veterinary_app.dto.request.Animal.AnimalSaveRequest;
import dev.veterinary.veterinary_app.dto.request.Doctor.DoctorSaveRequest;
import dev.veterinary.veterinary_app.dto.request.Vaccine.VaccineSaveRequest;
import dev.veterinary.veterinary_app.dto.request.Vaccine.VaccineUpdateRequest;
import dev.veterinary.veterinary_app.dto.response.Animal.AnimalResponse;
import dev.veterinary.veterinary_app.dto.response.Doctor.DoctorResponse;
import dev.veterinary.veterinary_app.dto.response.Vaccine.VaccineResponse;
import dev.veterinary.veterinary_app.entities.Animal;
import dev.veterinary.veterinary_app.entities.Customer;
import dev.veterinary.veterinary_app.entities.Doctor;
import dev.veterinary.veterinary_app.entities.Vaccine;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.authenticator.SavedRequest;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/vaccines")
@RequiredArgsConstructor
public class VaccineController {

    private final IVaccineService vaccineService;
    private final IModelMapperService modelMapper;
    private final IAnimalService animalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<VaccineResponse> save(@Valid @RequestBody VaccineSaveRequest vaccineSaveRequest){

        Animal animal = this.animalService.get(vaccineSaveRequest.getAnimal_id());
        vaccineSaveRequest.setAnimal_id(0L);

        Vaccine saveVaccine = this.modelMapper.forRequest().map(vaccineSaveRequest,Vaccine.class);
        saveVaccine.setAnimal(animal);

        this.vaccineService.save(saveVaccine);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveVaccine , VaccineResponse.class));

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResultData<VaccineResponse> get(@PathVariable("id") Long id){
        Vaccine vaccine = this.vaccineService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(vaccine , VaccineResponse.class));
    }


    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<VaccineResponse> update(@Valid @RequestBody VaccineUpdateRequest vaccineUpdateRequest) {
        Animal animal =  this.animalService.get(vaccineUpdateRequest.getAnimal_id());
        this.vaccineService.get(vaccineUpdateRequest.getId());
        Vaccine updateVaccine = this.modelMapper.forRequest().map(vaccineUpdateRequest, Vaccine.class);
        updateVaccine.setAnimal(animal);
        this.vaccineService.update(updateVaccine);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateVaccine, VaccineResponse.class));
    }



    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<VaccineResponse>> cursor(
            @RequestParam(name = "page" , required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize" ,required = false,defaultValue = "5") int pageSize
    ){
        Page<Vaccine> vaccinePage = this.vaccineService.cursor(page,pageSize);
        Page<VaccineResponse> vaccineResponsePage = vaccinePage.map(vaccine ->
                this.modelMapper.forResponse().map(vaccine,VaccineResponse.class));
        return ResultHelper.cursor(vaccineResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.vaccineService.delete(id);
        return ResultHelper.ok();
    }


    @GetMapping("/findByDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Vaccine>  getVaccineByDate(
            @RequestParam(name = "start_date" ) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate start_date,
            @RequestParam(name = "finish_date" )@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finish_date
            ){
        return this.vaccineService.findByDate(start_date,finish_date);
    }



}
