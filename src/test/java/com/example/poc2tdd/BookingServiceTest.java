package com.example.poc2tdd;

import com.example.poc2tdd.model.Booking;
import com.example.poc2tdd.repository.BookingRepository;
import com.example.poc2tdd.service.BookingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class BookingServiceTest {

    @TestConfiguration
    static class BookingServiceTestConfiguration{

        @Bean
        public BookingService bookingService() {
            return new BookingService();
        }
    }

    @Autowired
    BookingService bookingService;

    @MockBean
    BookingRepository bookingRepository;

    @Before
    public void setup(){
        LocalDate checkIn = LocalDate.parse("2020-11-10");
        LocalDate checkOut = LocalDate.parse("2020-11-20");
        Booking booking = new Booking("1", "Henrique", checkIn, checkOut, 2);

        Mockito.when(bookingRepository.findByReserveName(booking.getReserveName()))
                .thenReturn(Optional.of(booking));
    }

    @Test
    public void bookingTestServiceDaysCalculator(){
        String name = "Henrique";
        int days = bookingService.daysCalculatorWithDatabase(name);

        Assertions.assertEquals(10, days);
    }
}
