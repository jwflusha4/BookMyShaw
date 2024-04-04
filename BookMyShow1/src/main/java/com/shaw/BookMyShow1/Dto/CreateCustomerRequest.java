package com.shaw.BookMyShow1.Dto;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private String name;
    private String email;
    private String password;
}
