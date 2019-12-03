package pl.edu.pw.eiti.wsd.bar_finder.bar_agent;

import jade.domain.FIPAAgentManagement.ServiceDescription;
import pl.edu.pw.eiti.wsd.bar_finder.BarFinderAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BarOfferManager;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT;

public class BarAgent extends BarFinderAgent {

    protected void setup() {
        System.out.println("Hello World! My name is " + getLocalName());

        ServiceDescription sd  = new ServiceDescription();
        sd.setType(BAR_AGENT);
        sd.setName(getLocalName());
        register(sd);

        addBehaviour(new BarOfferManager());
    }
}
