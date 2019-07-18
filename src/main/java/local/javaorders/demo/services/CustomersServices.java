package local.javaorders.demo.services;

import local.javaorders.demo.model.Customers;

import java.util.List;

public interface CustomersServices
{
    List<Customers> findAllCustomers();

    Customers findCustomerByName(String name);

    Customers save(Customers customer);

    Customers update(Customers customers, long custcode);

    void delete(long custcode);

}
