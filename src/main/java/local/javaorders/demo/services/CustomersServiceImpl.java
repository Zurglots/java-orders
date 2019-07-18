package local.javaorders.demo.services;


import local.javaorders.demo.model.Customers;
import local.javaorders.demo.model.Orders;
import local.javaorders.demo.repos.CustomersRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "customersService")
public class CustomersServiceImpl implements CustomersServices
{
    @Autowired
    private CustomersRepository custrepos;

    @Override
    public List<Customers> findAllCustomers()
    {
        List<Customers> list = new ArrayList<>();
        custrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Customers findCustomerByName(String name)
    {
        Customers customer = custrepos.findByCustname(name);

        if (customer == null)
        {
            throw new EntityNotFoundException("Customer" + name + " not found!");
        }
        return customer;
    }

    @Transactional
    @Override
    public Customers save(Customers customer)
    {
        Customers newCustomer = new Customers();

        newCustomer.setCustname(customer.getCustname());
        newCustomer.setCustcity(customer.getCustcity());
        newCustomer.setWorkingarea(customer.getWorkingarea());
        newCustomer.setCustcountry(customer.getCustcountry());
        newCustomer.setGrade(customer.getGrade());
        newCustomer.setOpeningamt(customer.getOpeningamt());
        newCustomer.setReceiveamt(customer.getReceiveamt());
        newCustomer.setPaymentamt(customer.getPaymentamt());
        newCustomer.setOutstandingamt(customer.getOutstandingamt());
        newCustomer.setPhone(customer.getPhone());
//        newCustomer.setAgent(customer.getAgent());

        for (Orders o : customer.getOrders())
        {
            newCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), o.getOrddescription(), newCustomer));
        }
        return custrepos.save(newCustomer);
    }

    @Override
    public Customers update(Customers customers, long custcode) {
        return null;
    }

    @Override
    public void delete(long custcode) {

    }
}
