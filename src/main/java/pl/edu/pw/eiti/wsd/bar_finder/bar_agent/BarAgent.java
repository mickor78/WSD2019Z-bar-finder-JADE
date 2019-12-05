package pl.edu.pw.eiti.wsd.bar_finder.bar_agent;

import java.util.List;

import jade.core.AID;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import pl.edu.pw.eiti.wsd.bar_finder.BarFinderAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BarOfferManager;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Bar;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.BarBeer;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderAgentNameUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.*;

public class BarAgent extends BarFinderAgent {

    private AID loudnessControllerAgentAID;
    private AID seatsControllerAgentAID;
    private AID resourcesControllerAgentAID;

    private Bar bar;

    public AID getLoudnessControllerAgentAID() {
        return loudnessControllerAgentAID;
    }
    public void setLoudnessControllerAgentAID(AID loudnessControllerAgentAID) {
        this.loudnessControllerAgentAID = loudnessControllerAgentAID;
    }

    public AID getSeatsControllerAgentAID() {
        return seatsControllerAgentAID;
    }
    public void setSeatsControllerAgentAID(AID seatsControllerAgentAID) {
        this.seatsControllerAgentAID = seatsControllerAgentAID;
    }

    public AID getResourcesControllerAgentAID() {
        return resourcesControllerAgentAID;
    }
    public void setResourcesControllerAgentAID(AID resourcesControllerAgentAID) {
        this.resourcesControllerAgentAID = resourcesControllerAgentAID;
    }

    public List<BarBeer> getResourcesInfo() {
        return this.bar.getBeers();
    }
    public void setResourcesInfo(List<BarBeer> resourcesInfo) {
        this.bar.setBeers(resourcesInfo);
    }

    public Integer getFreeSeatsNumber() {
        return this.bar.getFreeSeats();
    }
    public void setFreeSeatsNumber(Integer seatsNumber) {
        this.bar.setFreeSeats(seatsNumber);
    }

    public Bar.LoudnessLevel getLoudnessLevel() {
        return this.bar.getLoudnessLevel();
    }
    public void setLoudnessLevel(Bar.LoudnessLevel loudnessLevel) {
        this.bar.setLoudnessLevel(loudnessLevel);
    }

    protected void setup() {
        System.out.println("Hello World! My name is " + getLocalName());

        // Get agent parameters
        Object[] args = getArguments();
        if (args != null && args.length > 0)
            this.bar = (Bar)args[0];

        if (this.bar == null) {
            doDelete();
        }
        else {
            // Register agent
            ServiceDescription sd = new ServiceDescription();
            sd.setType(BAR_AGENT);
            sd.setName(getLocalName());
            register(sd);

            loudnessControllerAgentAID = new AID(
                    BarFinderAgentNameUtils.GetBarControllerName(bar.getName(), LOUDNESS_CONTROLLER_AGENT_NAME),
                    AID.ISLOCALNAME);
            seatsControllerAgentAID = new AID(
                    BarFinderAgentNameUtils.GetBarControllerName(bar.getName(), SEATS_CONTROLLER_AGENT_NAME),
                    AID.ISLOCALNAME);
            resourcesControllerAgentAID = new AID(
                    BarFinderAgentNameUtils.GetBarControllerName(bar.getName(), RESOURCES_CONTROLLER_AGENT_NAME),
                    AID.ISLOCALNAME);

            addBehaviour(new BarOfferManager());
        }
    }
}
