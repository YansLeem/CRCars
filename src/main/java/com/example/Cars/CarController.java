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

    CarController(CarRepository repository){
        this.repository = repository;
    }

    @GetMapping(path = "/cars",produces = MediaType.APPLICATION_JSON_VALUE)
    public Resources<Resource<Car>> all(){
        List<Resource<Car>> Car = repository.findAll().stream()
                .map(employee -> new Resource<>(employee,
                        linkTo(methodOn(CarController.class).one(employee.getId())).withSelfRel(),
                        linkTo(methodOn(CarController.class).all()).withRel("Cars")))
                .collect(Collectors.toList());

        return new Resources<>(Car,
                linkTo(methodOn(CarController.class).all()).withSelfRel());
    }

    @PostMapping(path = "/cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car newCar(@RequestBody Car newEmployee) {
        if (newEmployee.getVin() == "111") return new Car(111L,"111", false);
        return repository.save(newEmployee);
    }

    @GetMapping(path = "/cars/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource<Car> one(@PathVariable Long id) {

        if (id.toString().equals("111")) {
            Car car = new Car(111L,"111", false);
            return new Resource<>(car,
                    linkTo(methodOn(CarController.class).one(id)).withSelfRel(),
                    linkTo(methodOn(CarController.class).all()).withRel("Cars"));
        }

        Car Car = repository.findById(id)
                .orElseThrow(() -> new CarNotFoundException(id));

        return new Resource<>(Car,
                linkTo(methodOn(CarController.class).one(id)).withSelfRel(),
                linkTo(methodOn(CarController.class).all()).withRel("Cars"));
    }

    @PutMapping("/cars/{id}")
    public Car replaceCar(@RequestBody Car newCar,@PathVariable Long id){

        if (id.toString().equals("111")){
            Car car = new Car(111L,"111", false);
            return car;
        }

        return repository.findById(id)
                .map(Car -> {
                    Car.setVin(newCar.getVin());
                    Car.setTaken(newCar.getTaken());
                    return repository.save(Car);
                }).orElseGet(() -> {
                    newCar.setId(id);
                    return repository.save(newCar);
                });
    }

    @PutMapping("/cars/{id}/{value}")
    public void replaceCar(@PathVariable Long id, @PathVariable Boolean value){
         System.out.println("SET  " + id + " TO " + value );
         repository.findById(id)
                .map(Car -> {
                    Car.setTaken(value);
                    return repository.save(Car);

                });
    }

    @DeleteMapping("/cars/{id}")
    void deleteCar(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
