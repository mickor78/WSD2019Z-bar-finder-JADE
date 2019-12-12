package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import java.util.List;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Bar;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.BarBeer;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.*;

public class GetControllerAgentResponse extends CyclicBehaviour {

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
            ACLMessage response = myAgent.receive(MessageTemplate.MatchSender(controllerAID));
            if (response != null) {
                switch (controllerType) {
                    case LOUDNESS_CONTROLLER_AGENT:
                        Bar.LoudnessLevel loudnessLevel = (Bar.LoudnessLevel)response.getContentObject();
                        getAgent().setLoudnessLevel(loudnessLevel);
                        ConsolePrintingMsgUtils.PrintMsg(myAgent.getLocalName() + " (BOM) - receives from " +
                                response.getSender().getLocalName() + ": " + loudnessLevel.name());
                        break;
                    case SEATS_CONTROLLER_AGENT:
                        Integer freeSeatsNumber = (Integer)response.getContentObject();
                        ConsolePrintingMsgUtils.PrintMsg(myAgent.getLocalName() + " (BOM) - receives from " +
                                response.getSender().getLocalName() + ": " + freeSeatsNumber);
                        getAgent().setFreeSeatsNumber(freeSeatsNumber);
                        break;
                    case RESOURCES_CONTROLLER_AGENT:
                        List<BarBeer> resourcesInfo = (List<BarBeer>)response.getContentObject();
                        if (resourcesInfo != null) {
                            if (!resourcesInfo.isEmpty()) {
                                String msgText = myAgent.getLocalName() + " (BOM) - receives from " + response.getSender().getLocalName() + ":";
                                for (BarBeer beer : resourcesInfo) {
                                    msgText = msgText.concat(System.lineSeparator() + beer.toString());
                                }
                                getAgent().setResourcesInfo(resourcesInfo);
                                ConsolePrintingMsgUtils.PrintMsg(msgText);
                            } else {
                                // TODO:
                                System.out.println(myAgent.getLocalName() + " (BOM) - receives empty resources information from "
                                    + response.getSender().getLocalName());
                            }
                        }
                        else {
                            // TODO:
                            System.out.println(myAgent.getLocalName() + " (BOM) - receives wrong data from " + response.getSender().getLocalName());
                        }
                        break;
                }
            } else {
                block();
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
