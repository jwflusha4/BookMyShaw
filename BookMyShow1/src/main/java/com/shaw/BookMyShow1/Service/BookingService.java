package com.shaw.BookMyShow1.Service;

import com.shaw.BookMyShow1.Model.*;
import com.shaw.BookMyShow1.Repository.BookingRepository;
import com.shaw.BookMyShow1.Repository.CustomerRepository;
import com.shaw.BookMyShow1.Repository.MovieShowRepository;
import com.shaw.BookMyShow1.Repository.MovieShowSeatRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service

public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MovieShowRepository movieShowRepository;
    @Autowired
    private MovieShowSeatRepository movieShowSeatRepository;

    public synchronized Booking createBooking(Long customerId, Long movieShowId, List<Long> seatIds){
        Date lockExpirationTime = calculateLockExpirationTime();
        Customer tempCustomer=customerRepository.getById(customerId);
        if(tempCustomer==null){
            throw new IllegalArgumentException("Customer not found!!!");
        }
        MovieShow tempMovieShow=movieShowRepository.getById(movieShowId);
        if(tempMovieShow==null){
            throw new IllegalArgumentException("Wrong movie show choosen!!!");
        }
        //List<MovieShowSeat> seats =
        List<MovieShowSeat> tempList=lockSeats(seatIds);
        for(MovieShowSeat it:tempList){
            if(it.getStatus()!= BookingSeatStatus.RESERVED){
                throw new IllegalArgumentException("Seat is Already Booked!!!");
            }
        }
        for(MovieShowSeat it:tempList){
            it.setStatus(BookingSeatStatus.BOOKED);
            movieShowSeatRepository.save(it);
        }
        Booking booking=new Booking();
        booking.setAmount(100);
        booking.setCustomer(tempCustomer);
        booking.setMovieShow(tempMovieShow);
        booking.setShowSeats(tempList);
        bookingRepository.save(booking);
        return booking;

    }
    private List<MovieShowSeat> lockSeats(List<Long>seatIds){
        Date lockExpirationTime = calculateLockExpirationTime();
        List<MovieShowSeat> lockedSeats=new ArrayList<>();
        for (Long seatId : seatIds) {
            MovieShowSeat seat = movieShowSeatRepository.findById(seatId)
                    .orElseThrow(() -> new IllegalArgumentException("Seat not found"));
            if (seat.getStatus() == BookingSeatStatus.AVAILABLE) {
                seat.setStatus(BookingSeatStatus.RESERVED);
                seat.setLockExpirationTime(lockExpirationTime);
                lockedSeats.add(seat);
            } else {
                throw new IllegalStateException("Seat " + seatId + " is not available.");
            }
        }
        return movieShowSeatRepository.saveAll(lockedSeats);
    }
    @Scheduled(fixedDelay = 60000) // Run every minute
    public void unlockExpiredSeats() {
        Date currentTime = new Date();
        List<MovieShowSeat> expiredSeats = movieShowSeatRepository.findExpiredLockedSeats(currentTime);
        for (MovieShowSeat seat : expiredSeats) {
            seat.setStatus(BookingSeatStatus.AVAILABLE);
            movieShowSeatRepository.save(seat);
        }
    }
    private Date calculateLockExpirationTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, 15);
        return calendar.getTime();
    }

}
