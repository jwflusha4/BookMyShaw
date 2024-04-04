package com.shaw.BookMyShow1.Repository;

import com.shaw.BookMyShow1.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking,Long> {
}
