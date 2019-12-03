package pl.edu.pw.eiti.wsd.bar_finder.customer_agent;

import jade.core.AID;
import jade.core.Agent;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import pl.edu.pw.eiti.wsd.bar_finder.BarFinderAgent;
import pl.edu.pw.eiti.wsd.bar_finder.customer_agent.behaviours.FindBar;

import java.util.Arrays;
import java.util.List;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.CUSTOMER_AGENT;

public class CustomerAgent extends BarFinderAgent {

    protected void setup() {
        System.out.println("Hello World! My name is " + getLocalName());

        ServiceDescription sd  = new ServiceDescription();
        sd.setType(CUSTOMER_AGENT);
        sd.setName(getLocalName());
        register(sd);

        addBehaviour(new FindBar());
//        try {
//            Thread.sleep(100000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public List<AID> getBars(){
        return Arrays.asList(this.searchDF(BAR_AGENT));
    }
}
