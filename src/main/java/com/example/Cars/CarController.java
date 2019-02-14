package com.example.Cars;


import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@RestController

public class CarController {

    private final CarRepository repository;

    SeatController(CarRepository repository){
        this.repository = repository;
    }

    @GetMapping(path = "/cars",produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Resource<Car>> all(){
        List<Resource<Car>> Seats = repository.findAll().stream()
                .map(employee -> new Resource<>(employee,
                        linkTo(methodOn(CarController.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(CarController.class).all()).withRel("Cars")))
                .collect(Collectors.toList());

        return new Resources<>(Seats,
                linkTo(methodOn(SeatController.class).all()).withSelfRel());
    }

    @PostMapping(path = "/seats", produces = MediaType.APPLICATION_JSON_VALUE)
    public Seat newSeat(@RequestBody Seat newEmployee) {
        if (newEmployee.getNumber() == 777) return new Seat(771L,777, false);
        return repository.save(newEmployee);
    }

    @GetMapping(path = "/seats/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Seat> one(@PathVariable Long id) {

        if (id == 777) {
            Seat seat = new Seat(771L,777, false);
            return new Resource<>(seat,
                    linkTo(methodOn(SeatController.class).one(id)).withSelfRel(),
                    linkTo(methodOn(SeatController.class).all()).withRel("Seats"));
        }

        Seat Seat = repository.findById(id)
                .orElseThrow(() -> new SeatNotFoundException(id));

        return new Resource<>(Seat,
                linkTo(methodOn(SeatController.class).one(id)).withSelfRel(),
                linkTo(methodOn(SeatController.class).all()).withRel("Seats"));
    }

    @PutMapping("/seats/{id}")
    public Seat replaceSeat(@RequestBody Seat newSeat,@PathVariable Long id){

        if (id == 777){
            Seat person = new Seat(777L,777, false);
            return person;
        } else if (id == 111){
            Seat person = new Seat(111L,111, false);
            return person;
        }

        return repository.findById(id)
                .map(Seat -> {
                    Seat.setNumber(newSeat.getNumber());
                    Seat.setTaken(newSeat.getTaken());
                    return repository.save(Seat);
                }).orElseGet(() -> {
                    newSeat.setId(id);
                    return repository.save(newSeat);
                });
    }

    @PutMapping("/seats/{id}/{value}")
    public void replaceSeat(@PathVariable Long id, @PathVariable Boolean value){
         System.out.println("SET  " + id + " TO " + value );
         repository.findById(id)
                .map(Seat -> {
                    Seat.setTaken(value);
                    return repository.save(Seat);
                });
    }

    @DeleteMapping("/seats/{id}")
    void deleteSeat(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
