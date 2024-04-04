package com.shaw.BookMyShow1.Model;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Seat extends BaseModel {
    private int rowNum;
    private int colNum;
    @Enumerated(EnumType.ORDINAL)
    private SeatType seatType;

}