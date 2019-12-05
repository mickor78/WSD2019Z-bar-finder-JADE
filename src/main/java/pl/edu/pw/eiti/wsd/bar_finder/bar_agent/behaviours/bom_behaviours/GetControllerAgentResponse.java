package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import java.util.List;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import jade.lang.acl.UnreadableException;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Bar;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.BarBeer;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.*;

public class GetControllerAgentResponse extends OneShotBehaviour {

    private AID controllerAID;
    private String controllerType;

    public GetControllerAgentResponse(AID controllerAID, String controllerType){
        this.controllerAID = controllerAID;
        this.controllerType = controllerType;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void action() {
        try {
            ACLMessage response = myAgent.blockingReceive(MessageTemplate.MatchSender(controllerAID));
            if (response != null) {
                switch (controllerType) {
                    case LOUDNESS_CONTROLLER_AGENT:
                        Bar.LoudnessLevel loudnessLevel = (Bar.LoudnessLevel)response.getContentObject();
                        System.out.println(myAgent.getLocalName() + " - received from " + response.getSender().getLocalName() + ": "
                                + loudnessLevel.name());
                        getAgent().setLoudnessLevel(loudnessLevel);
                        break;
                    case SEATS_CONTROLLER_AGENT:
                        Integer freeSeatsNumber = (Integer)response.getContentObject();
                        System.out.println(myAgent.getLocalName() + " - received from " + response.getSender().getLocalName() + ": "
                                + freeSeatsNumber);
                        getAgent().setFreeSeatsNumber(freeSeatsNumber);
                        break;
                    case RESOURCES_CONTROLLER_AGENT:
                        List<BarBeer> resourcesInfo = (List<BarBeer>)response.getContentObject();
                        System.out.println(myAgent.getLocalName() + " - received from " + response.getSender().getLocalName() + ":");
                        if (resourcesInfo != null && !resourcesInfo.isEmpty()) {
                            for (BarBeer beer: resourcesInfo) {
                                System.out.println(beer.toString());
                            }
                            getAgent().setResourcesInfo(resourcesInfo);
                        }
                        else {
                            System.out.println("Empty resources info.");
                        }
                        break;
                }
            } else {
                // todo
            }
        }
        catch (UnreadableException ex)
        {
            // TODO
            ex.printStackTrace();
        }
    }

    public BarAgent getAgent(){
        return (BarAgent)myAgent;
    }

}
