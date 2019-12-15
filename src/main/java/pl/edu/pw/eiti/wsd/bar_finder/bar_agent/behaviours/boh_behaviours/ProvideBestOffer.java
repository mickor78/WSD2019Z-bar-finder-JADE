package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JadeUtils.sendMessage;

public class ProvideBestOffer extends OneShotBehaviour {

    @Override
    public void action() {
        AID customerAID = getBOH().getCustomerAID();
        double score = getBOH().getScore();
        if (customerAID != null) {
            ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH) - sends offer to customer %s.",
                myAgent.getLocalName(), customerAID.getLocalName()));
            // TODO: Może coś więcej, a nie tylko score?
            sendMessage(myAgent, ACLMessage.INFORM, null, customerAID, score);
        }
    }

    private BestOfferHolder getBOH() {
        return (BestOfferHolder) getParent();
    }
}
