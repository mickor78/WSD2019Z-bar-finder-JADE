package pl.edu.pw.eiti.wsd.bar_finder.customer_agent;

import java.util.Arrays;
import java.util.List;

import jade.core.AID;
import jade.domain.FIPAAgentManagement.ServiceDescription;

import pl.edu.pw.eiti.wsd.bar_finder.BarFinderAgent;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Preferences;
import pl.edu.pw.eiti.wsd.bar_finder.customer_agent.behaviours.FindBar;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.CUSTOMER_AGENT;

public class CustomerAgent extends BarFinderAgent {

    private Preferences preferences;

    protected void setup() {
        System.out.println("Hello World! My name is " + getLocalName());

        // Get agent parameters
        Object[] args = getArguments();
        if (args != null && args.length > 0)
            this.preferences = (Preferences)args[0];

        if (preferences == null) {
            doDelete();
        }
        else {
            // Register agent
            ServiceDescription sd = new ServiceDescription();
            sd.setType(CUSTOMER_AGENT);
            sd.setName(getLocalName());
            register(sd);

            addBehaviour(new FindBar());
        }
    }

    public List<AID> getBars(){
        return Arrays.asList(this.searchDF(BAR_AGENT));
    }
}
