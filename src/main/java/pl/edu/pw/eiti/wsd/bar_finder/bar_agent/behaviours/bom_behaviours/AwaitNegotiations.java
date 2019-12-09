package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BarOfferManager;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.common_behaviours.Negotiations;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT_ROLE_BOM;

public class AwaitNegotiations extends CyclicBehaviour {
    public void action() {

        ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.CFP));

        if (msg != null) {
            System.out.println(myAgent.getLocalName() + " - NEGOTIATIONS: " + msg.getContent());
            getBOM().addSubBehaviour(new Negotiations(BAR_AGENT_ROLE_BOM, msg.getSender().getLocalName() + myAgent.getLocalName(), msg.getSender()));
        } else
            block();
    }

    public BarOfferManager getBOM() {
        return (BarOfferManager) getParent();
    }

}
