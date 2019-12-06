package pl.edu.pw.eiti.wsd.bar_finder.customer_agent.behaviours;

import java.io.IOException;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import pl.edu.pw.eiti.wsd.bar_finder.customer_agent.CustomerAgent;

public class FindBar extends OneShotBehaviour {
    @Override
    public void action() {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        for (AID aid : getAgent().getBars()){
            msg.addReceiver(aid);
            System.out.println("Receiver found: " + aid.getLocalName());
        }
        try {
            msg.setContentObject(getAgent().getPreferences());
            myAgent.send(msg);
            myAgent.addBehaviour(new AwaitOffers());
        }
        catch (IOException e) {
            // TODO:
            e.printStackTrace();
        }
    }

    public CustomerAgent getAgent(){
        return (CustomerAgent)myAgent;
    }
}
