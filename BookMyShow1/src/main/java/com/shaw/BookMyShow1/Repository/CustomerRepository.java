package com.shaw.BookMyShow1.Repository;

import com.shaw.BookMyShow1.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
