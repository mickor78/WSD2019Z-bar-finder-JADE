package pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.seats_controller_agent;

import jade.core.Agent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.seats_controller_agent.behaviours.ProvideSeatsNumber;

public class SeatsControllerAgent extends Agent {

    protected void setup() {
        System.out.println("Hello World! My name is "+getLocalName());
        addBehaviour(new ProvideSeatsNumber());
    }

    public String checkSeatsNumber(){
        return "57";
    }
}