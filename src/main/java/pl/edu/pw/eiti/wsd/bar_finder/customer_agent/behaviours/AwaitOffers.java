package pl.edu.pw.eiti.wsd.bar_finder.customer_agent.behaviours;

import java.io.Serializable;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

public class AwaitOffers extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
        if (msg != null) {
            try {
                Serializable content = msg.getContentObject();
                if (content instanceof Double) {
                    double score = (Double) content;
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (customer) - receives offer: %s - %f.",
                        myAgent.getAID().getLocalName(), msg.getSender().getLocalName(), score));
                } else {
                    // TODO:
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (customer) - receives wrong data.",
                        myAgent.getAID().getLocalName()));
                }
            }
            catch (UnreadableException e) {
                // TODO:
                e.printStackTrace();
            }
        } else {
            block();
        }
    }
}
