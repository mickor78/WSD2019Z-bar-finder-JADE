package pl.edu.pw.eiti.wsd.bar_finder.customer_agent.behaviours;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import pl.edu.pw.eiti.wsd.bar_finder.customer_agent.CustomerAgent;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JadeUtils.sendMessage;

public class FindBar extends OneShotBehaviour {
    @Override
    public void action() {
        List<AID> receivers = new LinkedList<>();
        // TODO: Limit bars count.
        for (AID aid : getAgent().getBars()) {
            receivers.add(aid);
            ConsolePrintingMsgUtils.PrintMsg(getAgent().getLocalName() + " - " + "receiver found: " + aid.getLocalName());
        }

        CustomerAgent customerAgent = getAgent();
        sendMessage(customerAgent, ACLMessage.INFORM, receivers, customerAgent.getCodec(), customerAgent.getOntology(),
                customerAgent.getPreferences());
        myAgent.addBehaviour(new AwaitOffers());
    }

    public CustomerAgent getAgent() {
        return (CustomerAgent) myAgent;
    }
}
