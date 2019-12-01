package pl.edu.pw.eiti.wsd.bar_finder.system;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

public class BarFinderSystem
{
    public static void main(String[] args)
    {
        // Get the JADE runtime interface (singleton)
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        // Create profile for main container
        Profile mainProfile = new ProfileImpl();
        mainProfile.setParameter(Profile.MAIN_HOST, "localhost");
        ContainerController mainContainer = runtime.createMainContainer(mainProfile);
        // Create profile for customer container
        Profile customerProfile = new ProfileImpl();
        customerProfile.setParameter(Profile.MAIN_HOST, "localhost");
        customerProfile.setParameter(Profile.CONTAINER_NAME, "Customer-container");
        AgentContainer customerContainer = runtime.createAgentContainer(customerProfile);
        try {
            // Create rma agent that is responsible for gui.
            AgentController rma = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();
            AgentController ag = customerContainer.createNewAgent("customer",
                    "pl.edu.pw.eiti.wsd.bar_finder.customer_agent.CustomerAgent",
                    new Object[] {}); // Arguments
            ag.start();
        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
