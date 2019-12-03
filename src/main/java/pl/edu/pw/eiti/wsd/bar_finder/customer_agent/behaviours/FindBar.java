package pl.edu.pw.eiti.wsd.bar_finder.customer_agent.behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import pl.edu.pw.eiti.wsd.bar_finder.customer_agent.CustomerAgent;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT;

public class FindBar extends OneShotBehaviour {
    @Override
    public void action() {
        ACLMessage test = new ACLMessage(ACLMessage.INFORM);
        for(AID aid : getAgent().getBars()){
            test.addReceiver(aid);
            System.out.println("Receiver found: " + aid.getLocalName());
        }
        test.setContent("elo");
        myAgent.send(test);
        System.out.println(myAgent.getLocalName() + ": Test message sent.");
        myAgent.addBehaviour(new AwaitOffers());
    }

    public CustomerAgent getAgent(){
        return (CustomerAgent)myAgent;
    }
}
