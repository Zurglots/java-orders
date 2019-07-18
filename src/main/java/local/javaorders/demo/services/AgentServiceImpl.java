package local.javaorders.demo.services;


import local.javaorders.demo.model.Agent;
import local.javaorders.demo.repos.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "agentService")
public class AgentServiceImpl implements AgentServices
{
    @Autowired
    private AgentRepository agentrepos;

    @Override
    public List<Agent> findAll()
    {
        List<Agent> list = new ArrayList<>();
        agentrepos.findAll().iterator().forEachRemaining(list::add);

        // normal array list - you can sort the way you would normally here.
        return list;
    }

    @Override
    public Agent findAgentById(long id)
    {

        return agentrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public Agent findAgentByName(String name)
    {
        Agent agent = agentrepos.findByName(name);
        if (agent == null)
        {
            throw new EntityNotFoundException("Agent" + name + " not found!");
        }
        return agent;
    }

    @Override
    public void delete(long id)
    {
        if (agentrepos.findById(id).isPresent())
        {
            agentrepos.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Override
    public Agent save(Agent agent)
    {
        Agent newAgent = new Agent();
        newAgent.setAgentname(agent.getAgentname());

        return agentrepos.save(newAgent);
    }

    @Override
    public Agent update(Agent agent) {
        return null;
    }
}
