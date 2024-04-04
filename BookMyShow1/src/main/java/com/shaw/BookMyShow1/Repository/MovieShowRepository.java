package com.shaw.BookMyShow1.Repository;

import com.shaw.BookMyShow1.Model.MovieShow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieShowRepository extends JpaRepository<MovieShow,Long> {
}
