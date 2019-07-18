package local.javaorders.demo.controllers;


import local.javaorders.demo.model.Customers;
import local.javaorders.demo.services.CustomersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/data")
public class DataController
{
    @Autowired
    CustomersServices customersServices;

    @PostMapping(value = "/customer/new",
                produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(@Valid @RequestBody Customers newCustomer) throws URISyntaxException // @valid = if structure is ok, add to customer, it no cause error.
    {
        customersServices.save(newCustomer);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
        // set location header to the newly created resource
//        HttpHeaders responseHeaders = new HttpHeaders();
//        URI newRestaurantURI = ServletUriComponentsBuilder.fromCurrentRequest().path("/{restaurantid}").buildAndExpand(newCustomer.getCustomerById()).toUri();
//        responseHeaders.setLocation(newRestaurantURI);

    }

    @PutMapping(value = "/customer/update/{custcode}",
                        produces = {"application/json"})
    public ResponseEntity<?> updateCustomer(@RequestBody Customers updateCustomer, @PathVariable long custcode)
    {
        customersServices.update(updateCustomer, custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/customer/delete/{custcode}",
                    produces = {"application/json"})
    public ResponseEntity<?> deleteCustomer(@PathVariable long custcode)
    {
        customersServices.delete(custcode);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
