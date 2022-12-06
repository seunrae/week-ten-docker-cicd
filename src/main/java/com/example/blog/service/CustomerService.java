package com.example.blog.service;

import com.example.blog.dtos.customerdtos.CustomerLoginDto;
import com.example.blog.dtos.customerdtos.CustomerRegisterDto;
import com.example.blog.models.Customer;
import com.example.blog.repositories.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String registerCustomer(CustomerRegisterDto customerRegisterDto){
        Customer customer = new Customer();
        customer.setCustomerUserName(customerRegisterDto.getCustomerUserName());
        customer.setCustomerEmail(customerRegisterDto.getCustomerEmail());
        customer.setCustomerPassword(customerRegisterDto.getCustomerPassword());
        customer.setCustomerPhoneNumber(customerRegisterDto.getCustomerPhoneNumber());
        customerRepository.save(customer);

        return "Thank you! "+customer.getCustomerUserName()+" your registration has been successful!!";
    }

    public String loginCustomer(CustomerLoginDto customerLoginDto){
        Customer customer = customerRepository.findByCustomerEmailAndCustomerPassword(customerLoginDto.getCustomerEmail(), customerLoginDto.getCustomerPassword());
        if(Objects.nonNull(customer)){
            return "Login successful!\nWelcome "+customer.getCustomerUserName();
        }
        else if (customerLoginDto.getCustomerEmail().equals("") && customerLoginDto.getCustomerPassword().equals("")){
            return "Enter your user name or password";
        }
        return "Enter correct credentials";
    }

    public String updateCustomer (CustomerRegisterDto customerRegisterDto, Long customerId){
       Customer customer = customerRepository.findById(customerId).get();
       customer.setCustomerUserName(customerRegisterDto.getCustomerUserName());
       customer.setCustomerEmail(customerRegisterDto.getCustomerEmail());
       customer.setCustomerPassword(customerRegisterDto.getCustomerPassword());
       customer.setCustomerPhoneNumber(customerRegisterDto.getCustomerPhoneNumber());

       customerRepository.save(customer);

       return "Credentials Updated";
    }

    public String deleteCustomer(Long customerId){
      customerRepository.deleteById(customerId);
        return "Account deleted!";
    }

    public List<Customer> showCustomers(){
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    public Customer showCustomer (Long customerId){
        Customer customer = customerRepository.findById(customerId).get();
        return customer;
    }
}
