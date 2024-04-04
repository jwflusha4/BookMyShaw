package com.shaw.BookMyShow1.Dto;

import lombok.Data;

import java.util.List;
@Data
public class CreateBookingRequest {
    private Long userId;
    private Long showId;
    private List<Long> seatIds;
}
