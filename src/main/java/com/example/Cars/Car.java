package com.example.Cars;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Car {
    private @Id Long id;
    private Integer Number;
    private Boolean Taken =false;

    Car(Long id, Integer number, Boolean taken){
        this.id = id;
        this.Number = number;
        this.Taken = taken;
    }

    public void setTaken(Boolean taken) {
        this.Taken = taken;
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





