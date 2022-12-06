package com.example.blog.controller;

import com.example.blog.dtos.customerdtos.CustomerLoginDto;
import com.example.blog.dtos.customerdtos.CustomerRegisterDto;
import com.example.blog.models.Customer;
import com.example.blog.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/show-customers")
    public ResponseEntity<List<Customer>> showAllCustomer(){
        List<Customer> customers = customerService.showCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

    @GetMapping("/show-customer/{customerId}")
    public ResponseEntity<Customer> showCustomer(@PathVariable Long customerId){
        Customer customer = customerService.showCustomer(customerId);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @GetMapping("register-customer")
    public ResponseEntity<String> createCustomer(@RequestBody CustomerRegisterDto customerRegisterDto){
        String newCust = customerService.registerCustomer(customerRegisterDto);
        return new ResponseEntity<>(newCust, HttpStatus.CREATED);
    }

    @PostMapping("login-customer")
    public ResponseEntity<String> loginCustomer(@RequestBody CustomerLoginDto customerLoginDto){
        String authCust = customerService.loginCustomer(customerLoginDto);
        return  new ResponseEntity<>(authCust, HttpStatus.ACCEPTED);
    }

    @PatchMapping("update-customer/{customerId}")
    public ResponseEntity<String> updateCustomer(@RequestBody CustomerRegisterDto customerRegisterDto, @PathVariable Long customerId){
        String updtCust = customerService.updateCustomer(customerRegisterDto,customerId);
        return new ResponseEntity<>(updtCust, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("delete-customer/{customerId}")
    public  ResponseEntity<String> deleteCustomer(@PathVariable Long customerId){
        String delCust = customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(delCust, HttpStatus.OK);
    }



}
