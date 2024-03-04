package dev.veterinary.veterinary_app.api;

import dev.veterinary.veterinary_app.business.abstracts.IAvailableDateService;
import dev.veterinary.veterinary_app.business.abstracts.IDoctorService;
import dev.veterinary.veterinary_app.core.config.modelMapper.IModelMapperService;
import dev.veterinary.veterinary_app.core.result.Result;
import dev.veterinary.veterinary_app.core.result.ResultData;
import dev.veterinary.veterinary_app.core.utilies.ResultHelper;
import dev.veterinary.veterinary_app.dao.IAvailableDateRepo;
import dev.veterinary.veterinary_app.dto.CursorResponse;
import dev.veterinary.veterinary_app.dto.request.AvailableDate.AvailableDateSaveRequest;
import dev.veterinary.veterinary_app.dto.request.AvailableDate.AvailableDateUpdateRequest;
import dev.veterinary.veterinary_app.dto.request.Customer.CustomerSaveRequest;
import dev.veterinary.veterinary_app.dto.response.Animal.AnimalResponse;
import dev.veterinary.veterinary_app.dto.response.AvailableDate.AvailableDateResponse;
import dev.veterinary.veterinary_app.dto.response.Customer.CustomerResponse;
import dev.veterinary.veterinary_app.entities.AvailableDate;
import dev.veterinary.veterinary_app.entities.Customer;
import dev.veterinary.veterinary_app.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/availabledates")
@RequiredArgsConstructor
public class AvailableDateController {
    private final IAvailableDateService availableDateService;
    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AvailableDateResponse>  save(@Valid @RequestBody AvailableDateSaveRequest availableDateSaveRequest){
        Doctor doctor = this.doctorService.get(availableDateSaveRequest.getDoctor_id());
        availableDateSaveRequest.setDoctor_id(0L);

        AvailableDate saveAvailableDate = this.modelMapper.forRequest().map(availableDateSaveRequest , AvailableDate.class);
        saveAvailableDate.setDoctor(doctor);

        this.availableDateService.save(saveAvailableDate);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAvailableDate , AvailableDateResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> get(@PathVariable("id")Long id ){
        AvailableDate availableDate = this.availableDateService.get(id);
        return  ResultHelper.success(this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.availableDateService.delete(id);
        return ResultHelper.ok();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AvailableDateResponse> update(@Valid @RequestBody AvailableDateUpdateRequest availableDateUpdateRequest){
       this.availableDateService.get(availableDateUpdateRequest.getId());
        AvailableDate updateAvailableDate = this.modelMapper.forRequest().map(availableDateUpdateRequest, AvailableDate.class);
        this.availableDateService.update(updateAvailableDate);

        return ResultHelper.success(this.modelMapper.forResponse().map(updateAvailableDate, AvailableDateResponse.class));

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AvailableDateResponse>> cursor(
            @RequestParam(name = "page" , required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize" ,required = false,defaultValue = "5") int pageSize
    ){
        Page<AvailableDate> availableDatePage = this.availableDateService.cursor(page,pageSize);
        Page<AvailableDateResponse> availableDateResponsePage = availableDatePage.map(availableDate ->
                this.modelMapper.forResponse().map(availableDate,AvailableDateResponse.class));
        return ResultHelper.cursor(availableDateResponsePage);
    }


}
