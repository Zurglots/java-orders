package local.javaorders.demo.repos;

import local.javaorders.demo.model.Orders;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<Orders, Long>
{
}
