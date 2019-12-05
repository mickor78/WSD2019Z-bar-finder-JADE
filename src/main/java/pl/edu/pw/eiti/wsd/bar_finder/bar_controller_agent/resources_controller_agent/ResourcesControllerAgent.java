package pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.resources_controller_agent;

import java.util.List;

import jade.core.Agent;

import pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.resources_controller_agent.behaviours.ProvideResourcesInfo;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.BarBeer;

public class ResourcesControllerAgent extends Agent {

    private List<BarBeer> beers;

    @SuppressWarnings("unchecked")
    protected void setup() {
        System.out.println("Hello World! My name is " + getLocalName());

        // Get agent parameters
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            this.beers = (List<BarBeer>)args[0];
        }

        if (beers == null) {
            doDelete();
        }
        else {
            addBehaviour(new ProvideResourcesInfo());
        }
    }

    // TODO: Some random beers state modification.
    public List<BarBeer> checkResourcesInfo(){
        return beers;
    }
}