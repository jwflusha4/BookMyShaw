package com.shaw.BookMyShow1.Service;

import com.shaw.BookMyShow1.Model.Customer;
import com.shaw.BookMyShow1.Repository.CustomerRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Data
public class CustomerService {
    private CustomerRepository customerRepository;

    public Customer signUp(String name, String email, String password) {
        System.out.println("Service layer called for creating customer.");

//        BCryptPasswordEncoder()


        Customer customer = new Customer();
        customer.setName(name);
        customer.setEmail(email);
        customer.setBookings(new ArrayList<>());

        // Call the repository layer and try to save that to DB.
        customerRepository.save(customer);

        return customer;
    }
}
