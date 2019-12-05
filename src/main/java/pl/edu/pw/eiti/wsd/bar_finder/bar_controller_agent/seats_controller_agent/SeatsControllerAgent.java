package pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.seats_controller_agent;

import java.util.Random;

import jade.core.Agent;

import pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.seats_controller_agent.behaviours.ProvideSeatsNumber;

public class SeatsControllerAgent extends Agent {

    private Integer seatsNumber;

    protected void setup() {
        System.out.println("Hello World! My name is " + getLocalName());

        // Get agent parameters
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            this.seatsNumber = (Integer)args[0];
        }

        addBehaviour(new ProvideSeatsNumber());
    }

    public Integer checkFreeSeatsNumber()
    {
        Random random = new Random();
        return random.nextInt(seatsNumber + 1);
    }
}