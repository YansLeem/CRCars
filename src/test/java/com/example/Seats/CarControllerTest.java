package com.example.Seats;

import com.example.Cars.Car;
import com.example.Cars.CarController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@RunWith(SpringJUnit4ClassRunner.class)
public class CarControllerTest {

        @InjectMocks
        private CarController carController;

        private MockMvc mockMvc;

        ObjectMapper om = new ObjectMapper();

        @Before
        public void setup() throws Exception{
            mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
        }

        @Test
        public void testGet() throws Exception {
            mockMvc.perform(get("/cars/111")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.vin", Matchers.is("111")));
        }


        @Test
        public void testPut() throws Exception {
            Car newcar = new Car(111L, "111",false);
            String jsonRequest = om.writeValueAsString(newcar);
            MvcResult result = mockMvc.perform(put("/cars/111").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk()).andReturn();

            String resultContent = result.getResponse().getContentAsString();
            Car car = om.readValue(resultContent,Car.class);
            System.out.println("HERE >>>" + car.getVin() + " <<< HERE");

        }




        @Test
        public void testPost() throws Exception {
            Car newcar = new Car(111L, "111",false);
            String jsonRequest = om.writeValueAsString(newcar);
            MvcResult result = mockMvc.perform(post("/cars").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk()).andReturn();

            String resultContent = result.getResponse().getContentAsString();
            Car car = om.readValue(resultContent,Car.class);
            System.out.println("HERE >>>" + car.getVin() + " <<< HERE");

        }


}