package local.javaorders.demo.repos;

import local.javaorders.demo.model.Customers;
import org.springframework.data.repository.CrudRepository;

public interface CustomersRepository extends CrudRepository<Customers, Long>
{

}
