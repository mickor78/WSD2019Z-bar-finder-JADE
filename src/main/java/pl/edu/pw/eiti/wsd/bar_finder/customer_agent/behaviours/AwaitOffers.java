package pl.edu.pw.eiti.wsd.bar_finder.customer_agent.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

public class AwaitOffers extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();

        if (msg != null) {
            ConsolePrintingMsgUtils.PrintMsg(myAgent.getLocalName() + " - received: " + msg.getContent());
        } else {
            block();
        }
    }
}
