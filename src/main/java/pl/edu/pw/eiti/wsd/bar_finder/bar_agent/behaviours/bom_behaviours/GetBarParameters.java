package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;

public class GetBarParameters extends TickerBehaviour {
    public GetBarParameters(Agent a, long period) {
        super(a, period);
    }

    protected void onTick() {
        // update bar parameters
    }
}
