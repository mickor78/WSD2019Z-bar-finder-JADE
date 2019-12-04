package pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.resources_controller_agent;

import jade.core.Agent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.resources_controller_agent.behaviours.ProvideResourcesInfo;

public class ResourcesControllerAgent extends Agent {

    protected void setup() {
        System.out.println("Hello World! My name is "+getLocalName());
        addBehaviour(new ProvideResourcesInfo());
    }

    public String checkResourcesInfo(){
        return "42.0";
    }
}