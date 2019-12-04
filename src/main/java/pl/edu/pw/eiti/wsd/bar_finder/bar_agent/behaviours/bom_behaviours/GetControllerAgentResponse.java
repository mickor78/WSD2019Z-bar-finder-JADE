package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.*;

public class GetControllerAgentResponse extends OneShotBehaviour {

    private AID controllerAID;
    private String controllerType;

    public GetControllerAgentResponse(AID controllerAID, String controllerType){
        this.controllerAID = controllerAID;
        this.controllerType = controllerType;
    }

    @Override
    public void action() {
        ACLMessage response = myAgent.blockingReceive(MessageTemplate.MatchSender(controllerAID));
        if (response!=null) {
            System.out.println(myAgent.getLocalName() + " - received from " + response.getSender().getLocalName() + ": " + response.getContent());
            switch(controllerType) {
                case LOUDNESS_CONTROLLER_AGENT:
                    getAgent().setLoudnessLevel(Double.parseDouble(response.getContent()));
                    break;
                case SEATS_CONTROLLER_AGENT:
                    getAgent().setSeatsNumber(Integer.parseInt(response.getContent()));
                    break;
                case RESOURCES_CONTROLLER_AGENT:
                    getAgent().setResourcesInfo(Double.parseDouble(response.getContent()));
                    break;
            }
        } else {
            // todo
        }
    }

    public BarAgent getAgent(){
        return (BarAgent)myAgent;
    }

}
