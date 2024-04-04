package com.shaw.BookMyShow1.Controller;

import com.shaw.BookMyShow1.Dto.CreateCustomerRequest;
import com.shaw.BookMyShow1.Dto.CreateCustomerResponse;
import com.shaw.BookMyShow1.Model.Customer;
import com.shaw.BookMyShow1.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bms")
@Controller
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @PostMapping("/addUser")
    public CreateCustomerResponse addUserInAccount( @RequestBody CreateCustomerRequest createCustomerRequest){
        Customer customer=customerService.signUp(createCustomerRequest.getName(),createCustomerRequest.getEmail(),createCustomerRequest.getPassword());

        return new CreateCustomerResponse("Success");
    }
}
