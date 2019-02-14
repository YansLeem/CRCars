package com.example.Cars;

public class CarNotFoundException extends RuntimeException {

        private static final long serialVersionUID = 1L;

        public CarNotFoundException(Long id) {
            super("Could not find Car " + id);
        }
}
