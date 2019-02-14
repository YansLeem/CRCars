package com.example.Cars;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Car {
    private @Id Long id;
    private String Vin;
    private Boolean Taken =false;

    Car(Long id, String vin, Boolean taken){
        this.id = id;
        this.Vin = vin;
        this.Taken = taken;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVin() {
        return Vin;
    }

    public void setVin(String vin) {
        Vin = vin;
    }

    public Boolean getTaken() {
        return Taken;
    }

    public void setTaken(Boolean taken) {
        Taken = taken;
    }
}


/*
    fetch(
  '/people',
    {
        method: 'POST',
                headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: 'Paul', lastName: 'Jackson' })
    }
).then(result => result.json().then(console.log))


   fetch(
  '/p',
    {
        method: PUT',
                headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ number: 17, taken: false })
    })
*/





