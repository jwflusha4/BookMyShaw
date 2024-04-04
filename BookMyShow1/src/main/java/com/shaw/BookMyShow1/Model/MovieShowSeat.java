package com.shaw.BookMyShow1.Model;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
public class MovieShowSeat extends BaseModel {
    @ManyToOne
    private MovieShow movieShow;
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.ORDINAL)
    private BookingSeatStatus status;
    @Temporal(TemporalType.DATE)
    @Column(name = "exp_date")
    private Date lockExpirationTime;
}