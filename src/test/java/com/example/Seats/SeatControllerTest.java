package com.example.Seats;

import com.example.Cars.Seat;
import com.example.Cars.SeatController;
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

import com.sun.xml.internal.xsom.impl.Ref;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


@RunWith(SpringJUnit4ClassRunner.class)
public class SeatControllerTest {

        @InjectMocks
        private SeatController seatController;

        private MockMvc mockMvc;

        ObjectMapper om = new ObjectMapper();

        @Before
        public void setup() throws Exception{
            mockMvc = MockMvcBuilders.standaloneSetup(seatController).build();
        }

        @Test
        public void testGet() throws Exception {
            mockMvc.perform(get("/seats/777")
                    .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.number", Matchers.is(777)));
        }


        @Test
        public void testPut() throws Exception {
            Seat newseat = new Seat(111L, 111,false);
            String jsonRequest = om.writeValueAsString(newseat);
            //System.out.println("HERE >>>"  + " <<< HERE");
            MvcResult result = mockMvc.perform(put("/seats/777").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk()).andReturn();

            String resultContent = result.getResponse().getContentAsString();
            Seat seat = om.readValue(resultContent,Seat.class);
            System.out.println("HERE >>>" + seat.getNumber() + " <<< HERE");

        }




        @Test
        public void testPost() throws Exception {
            Seat newseat = new Seat(777L, 777,false);
            String jsonRequest = om.writeValueAsString(newseat);
            //System.out.println("HERE >>>"  + " <<< HERE");
            MvcResult result = mockMvc.perform(post("/seats").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                    .andExpect(status().isOk()).andReturn();

            String resultContent = result.getResponse().getContentAsString();
            Seat seat = om.readValue(resultContent,Seat.class);
            System.out.println("HERE >>>" + seat.getNumber() + " <<< HERE");

        }


}