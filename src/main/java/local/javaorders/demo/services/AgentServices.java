package local.javaorders.demo.services;

import local.javaorders.demo.model.Agent;

import java.util.List;

public interface AgentServices
{
    List<Agent> findAll();

    Agent findAgentById(long id);

    Agent findAgentByName(String name);

    void delete(long id);

    Agent save(Agent agent);

    Agent update(Agent agent);
}
