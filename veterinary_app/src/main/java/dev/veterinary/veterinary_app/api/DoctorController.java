package dev.veterinary.veterinary_app.api;

import dev.veterinary.veterinary_app.business.abstracts.IDoctorService;
import dev.veterinary.veterinary_app.core.config.modelMapper.IModelMapperService;
import dev.veterinary.veterinary_app.core.result.Result;
import dev.veterinary.veterinary_app.core.result.ResultData;
import dev.veterinary.veterinary_app.core.utilies.ResultHelper;
import dev.veterinary.veterinary_app.dto.CursorResponse;
import dev.veterinary.veterinary_app.dto.request.Doctor.DoctorSaveRequest;
import dev.veterinary.veterinary_app.dto.request.Doctor.DoctorUpdateRequest;
import dev.veterinary.veterinary_app.dto.response.Customer.CustomerResponse;
import dev.veterinary.veterinary_app.dto.response.Doctor.DoctorResponse;
import dev.veterinary.veterinary_app.entities.Customer;
import dev.veterinary.veterinary_app.entities.Doctor;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import javax.print.Doc;
import java.security.DrbgParameters;

@RestController
@RequestMapping("/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService doctorService;
    private final IModelMapperService modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<DoctorResponse> save(@Valid @RequestBody DoctorSaveRequest doctorSaveRequest){
        Doctor saveDoctor = this.modelMapper.forRequest().map(doctorSaveRequest,Doctor.class);
        this.doctorService.save(saveDoctor);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveDoctor,DoctorResponse.class));
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private ResultData<DoctorResponse> get(@PathVariable("id") Long id){
        Doctor doctor = this.doctorService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(doctor , DoctorResponse.class));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    private ResultData<DoctorResponse> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest){
        this.doctorService.get(doctorUpdateRequest.getId());
        Doctor updateDoctor = this.modelMapper.forRequest().map(doctorUpdateRequest , Doctor.class);
        this.doctorService.update(updateDoctor);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateDoctor,DoctorResponse.class));
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<DoctorResponse>> cursor(
            @RequestParam(name = "page" , required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize" ,required = false,defaultValue = "5") int pageSize
    ){
        Page<Doctor> doctorPage = this.doctorService.cursor(page,pageSize);
        Page<DoctorResponse> doctorResponsePage = doctorPage.map(doctor ->
                this.modelMapper.forResponse().map(doctor,DoctorResponse.class));
        return ResultHelper.cursor(doctorResponsePage);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.doctorService.delete(id);
        return ResultHelper.ok();
    }


}
