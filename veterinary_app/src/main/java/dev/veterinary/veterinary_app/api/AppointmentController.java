package dev.veterinary.veterinary_app.api;

import dev.veterinary.veterinary_app.business.abstracts.IAnimalService;
import dev.veterinary.veterinary_app.business.abstracts.IAppointmentService;
import dev.veterinary.veterinary_app.business.abstracts.IDoctorService;
import dev.veterinary.veterinary_app.core.config.modelMapper.IModelMapperService;
import dev.veterinary.veterinary_app.core.result.Result;
import dev.veterinary.veterinary_app.core.result.ResultData;
import dev.veterinary.veterinary_app.core.utilies.ResultHelper;
import dev.veterinary.veterinary_app.dto.CursorResponse;
import dev.veterinary.veterinary_app.dto.request.Animal.AnimalSaveRequest;
import dev.veterinary.veterinary_app.dto.request.Appointment.AppointmentSaveRequest;
import dev.veterinary.veterinary_app.dto.request.Appointment.AppointmentUpdateRequest;
import dev.veterinary.veterinary_app.dto.response.Animal.AnimalResponse;
import dev.veterinary.veterinary_app.dto.response.Appointment.AppointmentResponse;
import dev.veterinary.veterinary_app.dto.response.Customer.CustomerResponse;
import dev.veterinary.veterinary_app.entities.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final IModelMapperService modelMapper;
    private final IDoctorService doctorService;
    private final IAnimalService animalService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<AppointmentResponse> save(@Valid @RequestBody AppointmentSaveRequest appointmentSaveRequest){

        Doctor doctor = this.doctorService.get(appointmentSaveRequest.getDoctor_id());
        appointmentSaveRequest.setDoctor_id(0L);

        Animal animal  =this.animalService.get(appointmentSaveRequest.getAnimal_id());
        appointmentSaveRequest.setAnimal_id(0L);

        Appointment saveAppointment = this.modelMapper.forRequest().map(appointmentSaveRequest,Appointment.class);

        saveAppointment.setDoctor(doctor);
        saveAppointment.setAnimal(animal);

        this.appointmentService.save(saveAppointment);
        return ResultHelper.created(this.modelMapper.forResponse().map(saveAppointment,AppointmentResponse.class));

    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> get(@PathVariable("id") Long id){
        Appointment appointment = this.appointmentService.get(id);
        return ResultHelper.success(this.modelMapper.forResponse().map(appointment,AppointmentResponse.class));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") Long id){
        this.appointmentService.delete(id);
        return ResultHelper.ok();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResultData<AppointmentResponse> update(@Valid @RequestBody AppointmentUpdateRequest appointmentUpdateRequest ){
      this.appointmentService.get(appointmentUpdateRequest.getId());
        Appointment updateAppointment= this.modelMapper.forRequest().map(appointmentUpdateRequest, Appointment.class);
        this.appointmentService.update(updateAppointment);
        return ResultHelper.success(this.modelMapper.forResponse().map(updateAppointment, AppointmentResponse.class));

    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<AppointmentResponse>> cursor(
            @RequestParam(name = "page" , required = false, defaultValue = "0") int page,
            @RequestParam(name = "pageSize" ,required = false,defaultValue = "5") int pageSize
    ){
        Page<Appointment> appointmentPage = this.appointmentService.cursor(page,pageSize);
        Page<AppointmentResponse> appointmentResponsePage = appointmentPage.map( appointment->
                this.modelMapper.forResponse().map(appointment,AppointmentResponse.class));
        return ResultHelper.cursor(appointmentResponsePage);
    }


    @GetMapping("/{id}/findByDateAndDoctor")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment>  getByDateDoctor(
            @RequestParam(name = "start_date" ) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start_date,
            @RequestParam(name = "finish_date" )@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finish_date,
            @PathVariable("id") Long id
    ){
        Doctor doctor = this.doctorService.get(id);
        return this.appointmentService.getByDateAndDoctor(start_date.atStartOfDay(),finish_date.atStartOfDay(),doctor);
    }


    @GetMapping("/{id}/findByDateAndAnimal")
    @ResponseStatus(HttpStatus.OK)
    public List<Appointment>  getByDateAnimal(
            @PathVariable("id") Long id,
            @RequestParam(name = "start_date" ) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate start_date,
            @RequestParam(name = "finish_date" )@DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate finish_date
    ){
        Animal animal = this.animalService.get(id);
        return this.appointmentService.getByDateTimeAndAnimal(start_date.atStartOfDay(),finish_date.atStartOfDay(),animal);
    }



}
