package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours.Negotiations;

import java.util.List;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT_ROLE_BOH;

public class SearchCompetitors extends Behaviour {
    private int step = 0;
    private int receivedResponses = 0;
    private int expectedResponses = 0;

    public void action() {
        switch (step)
        {
            case 0:
                ACLMessage cfp = new ACLMessage(ACLMessage.CFP);
                List<AID> receivers = getParentB().getNearbyBars();
                expectedResponses = receivers.size();
                System.out.println(myAgent.getLocalName() + " - found bars nearby:" + expectedResponses);
                if(expectedResponses == 0){
                    // no more nearby bars, send return best offer to client?
                    step = 2;
                } else {
                    for(AID receiver : receivers){
                        cfp.addReceiver(receiver);
                    }
                    cfp.setConversationId("start_negotiations");
                    myAgent.send(cfp);
                    step = 1;
                }
                break;
            case 1:
                ACLMessage response = myAgent.receive(MessageTemplate.MatchConversationId("start_negotiations"));
                if(response != null){
                    getParentB().addSubBehaviour(new Negotiations(BAR_AGENT_ROLE_BOH, myAgent.getLocalName()+ response.getSender().getLocalName(), response.getSender()));
                    receivedResponses++;
                    if(receivedResponses == expectedResponses)
                        step = 2;
                } else {
                    block();
                }
                break;
        }
    }

    public boolean done() {
        return step == 2;
    }

    public BarAgent getAgent(){
        return (BarAgent)myAgent;
    }

    public StartNegotiations getParentB(){
        return (StartNegotiations) getParent();
    }
}