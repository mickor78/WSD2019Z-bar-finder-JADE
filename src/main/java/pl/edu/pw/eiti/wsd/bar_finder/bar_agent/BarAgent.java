package pl.edu.pw.eiti.wsd.bar_finder.bar_agent;

import jade.core.AID;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import pl.edu.pw.eiti.wsd.bar_finder.BarFinderAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BarOfferManager;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT;

public class BarAgent extends BarFinderAgent {

    private AID loudnessControllerAgentAID;
    private AID seatsControllerAgentAID;
    private AID resourcesControllerAgentAID;

    //temp
    private double resourcesInfo;
    private int seatsNumber;
    private double loudnessLevel;

    protected void setup() {
        System.out.println("Hello World! My name is " + getLocalName());

        ServiceDescription sd  = new ServiceDescription();
        sd.setType(BAR_AGENT);
        sd.setName(getLocalName());
        register(sd);

        loudnessControllerAgentAID = new AID(getLocalName() + "_loudness_controller_agent", AID.ISLOCALNAME);
        seatsControllerAgentAID = new AID(getLocalName() + "_seats_controller_agent", AID.ISLOCALNAME);
        resourcesControllerAgentAID = new AID(getLocalName() + "_resources_controller_agent", AID.ISLOCALNAME);

        addBehaviour(new BarOfferManager());

    }


    public AID getLoudnessControllerAgentAID() {
        return loudnessControllerAgentAID;
    }

    public AID getSeatsControllerAgentAID() {
        return seatsControllerAgentAID;
    }

    public AID getResourcesControllerAgentAID() {
        return resourcesControllerAgentAID;
    }


    public void setLoudnessControllerAgentAID(AID loudnessControllerAgentAID) {
        this.loudnessControllerAgentAID = loudnessControllerAgentAID;
    }

    public void setSeatsControllerAgentAID(AID seatsControllerAgentAID) {
        this.seatsControllerAgentAID = seatsControllerAgentAID;
    }

    public void setResourcesControllerAgentAID(AID resourcesControllerAgentAID) {
        this.resourcesControllerAgentAID = resourcesControllerAgentAID;
    }

    public double getResourcesInfo() {
        return resourcesInfo;
    }

    public void setResourcesInfo(double resourcesInfo) {
        this.resourcesInfo = resourcesInfo;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public double getLoudnessLevel() {
        return loudnessLevel;
    }

    public void setLoudnessLevel(double loudnessLevel) {
        this.loudnessLevel = loudnessLevel;
    }

}
