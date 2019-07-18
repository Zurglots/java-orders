package local.javaorders.demo.repos;

import local.javaorders.demo.model.Agent;
import org.springframework.data.repository.CrudRepository;

public interface AgentRepository extends CrudRepository<Agent, Long> // takes Agent obj, and Long type as class
{
    Agent findByName(String name);
}
