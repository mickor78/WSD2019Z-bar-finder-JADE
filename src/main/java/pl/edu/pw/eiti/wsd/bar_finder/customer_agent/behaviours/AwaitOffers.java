package pl.edu.pw.eiti.wsd.bar_finder.customer_agent.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class AwaitOffers extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();

        if (msg != null) {
            System.out.println(myAgent.getLocalName() + " - received: " + msg.getContent());
        } else {
            block();
        }
    }
}
