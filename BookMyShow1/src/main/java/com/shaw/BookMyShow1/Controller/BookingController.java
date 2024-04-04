package com.shaw.BookMyShow1.Controller;

import com.shaw.BookMyShow1.Dto.CreateBookingRequest;
import com.shaw.BookMyShow1.Dto.CreateBookingResponse;
import com.shaw.BookMyShow1.Model.Booking;
import com.shaw.BookMyShow1.Service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bms")
@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;
    @PostMapping("/book")
    public CreateBookingResponse createBooking( @RequestBody CreateBookingRequest createBookingRequest){
        Booking booking=bookingService.createBooking(createBookingRequest.getUserId(),
                createBookingRequest.getShowId(),createBookingRequest.getSeatIds());
        return new CreateBookingResponse("Success!!");
    }
}
