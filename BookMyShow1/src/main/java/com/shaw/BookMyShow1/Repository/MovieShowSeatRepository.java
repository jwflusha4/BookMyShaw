package com.shaw.BookMyShow1.Repository;

import com.shaw.BookMyShow1.Model.MovieShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface MovieShowSeatRepository extends JpaRepository<MovieShowSeat, Long> {
    @Query("SELECT m FROM MovieShowSeat m WHERE m.status = com.shaw.BookMyShow1.Model.BookingSeatStatus.RESERVED AND m.lockExpirationTime < :currentTime")
    public List<MovieShowSeat>findExpiredLockedSeats(@Param("currentTime") Date currentTime);
}