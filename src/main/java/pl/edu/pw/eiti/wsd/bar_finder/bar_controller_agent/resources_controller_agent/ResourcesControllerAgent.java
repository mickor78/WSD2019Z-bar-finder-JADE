package pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.resources_controller_agent;

import java.util.List;
import java.util.Random;

import jade.core.Agent;

import pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.resources_controller_agent.behaviours.ProvideResourcesInfo;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.BarBeer;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

public class ResourcesControllerAgent extends Agent {

    private List<BarBeer> beers;

    @SuppressWarnings("unchecked")
    protected void setup() {
        ConsolePrintingMsgUtils.PrintMsg("Hello World! My name is " + getLocalName());

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

    public List<BarBeer> checkResourcesInfo(){
        Random random = new Random();

        for (BarBeer beer: beers) {
            int p = random.nextInt(20);
            if (p < 5) {
                double currentQuantity = beer.getQuantity();
                if (p > 0) {
                    if (currentQuantity >= 0.5) {
                        beer.setQuantity(currentQuantity - 0.5);
                    }
                    else
                        beer.setQuantity(0.0);
                }
                else {
                    if (currentQuantity <= 10.0)
                        beer.setQuantity(currentQuantity + 50.0);
                }
            }
        }
        return beers;
    }
}