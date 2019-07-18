package local.javaorders.demo.controllers;


import local.javaorders.demo.model.Customers;
import local.javaorders.demo.services.CustomersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController
{
    @Autowired
    private CustomersServices customersServices;

    @GetMapping
            (value = "/order",
                produces = {"application/json"})
    public ResponseEntity<?> findAllCustomers()
    {
        List<Customers> myCustomers = customersServices.findAllCustomers();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    @GetMapping
            (value = "/name/{custname}",
                produces = {"application/json"})
    public ResponseEntity<?> findCustomerByName(@PathVariable String custname)
    {
        Customers c = customersServices.findCustomerByName(custname);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }
}
