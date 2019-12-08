package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;

public class Negotations extends Behaviour {

    // TODO: Enum?
    private int state = 0;

    public void action() {
        switch (state)
        {
            case 0:
                ACLMessage msg = myAgent.receive();

                break;
            case 1:
                state++;
                break;
            case 2:
                state = 0;
                break;
        }
    }

    public boolean done() {
        return false;
    }

    public BarAgent getAgent(){
        return (BarAgent)myAgent;
    }

}
