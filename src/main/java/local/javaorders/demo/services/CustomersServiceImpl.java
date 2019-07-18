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
    public Customers findCustomerByName(String name) throws EntityNotFoundException
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
        newCustomer.setAgent(customer.getAgent());

        for (Orders o : customer.getOrders())
        {
            newCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), o.getOrddescription(), newCustomer));
        }
        return custrepos.save(newCustomer);
    }

    @Transactional
    @Override
    public Customers update(Customers customers, long custcode)
    {
        Customers currentCustomer = custrepos.findById(custcode).orElseThrow(() -> new EntityNotFoundException(Long.toString(custcode)));


        if (customers.getCustname() != null)
        {
            currentCustomer.setCustname(customers.getCustname());
        }

        if (customers.getCustcity() != null)
        {
            currentCustomer.setCustcity(customers.getCustcity());
        }

        if (customers.getWorkingarea() != null)
        {
            currentCustomer.setWorkingarea(customers.getCustcity());
        }

        if (customers.getCustcountry() != null)
        {
            currentCustomer.setCustcountry(customers.getCustcountry());
        }

        if (customers.getGrade() != null)
        {
            currentCustomer.setGrade(customers.getGrade());
        }

        if (customers.getOpeningamt() != 0)
        {
            currentCustomer.setOpeningamt(customers.getOpeningamt());
        }

        if (customers.getReceiveamt() != 0)
        {
            currentCustomer.setReceiveamt(customers.getReceiveamt());
        }

        if (customers.getPaymentamt() != 0)
        {
            currentCustomer.setPaymentamt(customers.getPaymentamt());
        }

        if (customers.getOutstandingamt() != 0)
        {
            currentCustomer.setOutstandingamt(customers.getOutstandingamt());
        }

        if (customers.getPhone() != null)
        {
            currentCustomer.setPhone(customers.getPhone());
        }

        if (customers.getAgent() != null)
        {
            currentCustomer.setAgent(customers.getAgent());
        }

        if (customers.getOrders().size() > 0)
        {
            for (Orders o : customers.getOrders())
            {
                currentCustomer.getOrders().add(new Orders(o.getOrdamount(), o.getAdvanceamount(), o.getOrddescription(), o.getCustomer()));
            }
        }

        return custrepos.save(currentCustomer);
    }

    @Transactional
    @Override
    public void delete(long custcode)
    {
        if (custrepos.findById(custcode).isPresent())
        {
            custrepos.deleteById(custcode);
        } else {
            throw new EntityNotFoundException(Long.toString(custcode));
        }
    }
}
