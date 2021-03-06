package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BarOfferManager;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.*;

public class GetBarParameters extends TickerBehaviour {

    private boolean firstRun = true;

    public GetBarParameters(Agent a, long period) {
        super(a, period);
    }

    protected void onTick() {

        if (firstRun) {
            getBOM().addSubBehaviour(new GetControllerAgentResponse(getAgent().getSeatsControllerAgentAID(), SEATS_CONTROLLER_AGENT));
            getBOM().addSubBehaviour(new GetControllerAgentResponse(getAgent().getLoudnessControllerAgentAID(), LOUDNESS_CONTROLLER_AGENT));
            getBOM().addSubBehaviour(new GetControllerAgentResponse(getAgent().getResourcesControllerAgentAID(), RESOURCES_CONTROLLER_AGENT));
            firstRun = false;
        }

        // update bar parameters
        ACLMessage seatsQuery = new ACLMessage(ACLMessage.QUERY_REF);
        seatsQuery.addReceiver(getAgent().getSeatsControllerAgentAID());
        ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM) - sends query to %s.",
            myAgent.getLocalName(), getAgent().getSeatsControllerAgentAID().getLocalName()));
        myAgent.send(seatsQuery);

        ACLMessage loudnessQuery = new ACLMessage(ACLMessage.QUERY_REF);
        loudnessQuery.addReceiver(getAgent().getLoudnessControllerAgentAID());
        ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM) - sends query to %s.",
                myAgent.getLocalName(), getAgent().getLoudnessControllerAgentAID().getLocalName()));
        myAgent.send(loudnessQuery);

        ACLMessage resourcesQuery = new ACLMessage(ACLMessage.QUERY_REF);
        resourcesQuery.addReceiver(getAgent().getResourcesControllerAgentAID());
        ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM) - sends query to %s.",
                myAgent.getLocalName(), getAgent().getResourcesControllerAgentAID().getLocalName()));
        myAgent.send(resourcesQuery);
    }

    public BarAgent getAgent() {
        return (BarAgent) myAgent;
    }

    private BarOfferManager getBOM() {
        return (BarOfferManager) getParent();
    }
}
