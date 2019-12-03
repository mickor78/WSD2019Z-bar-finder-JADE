package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours;

import jade.core.behaviours.ParallelBehaviour;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours.AwaitNegotiations;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours.GetBarParameters;

public class BarOfferManager extends ParallelBehaviour {

    public BarOfferManager(){
        this.addSubBehaviour(new GetBarParameters(myAgent, 30000));
        this.addSubBehaviour(new AwaitNegotiations());
    }

    public void addNegotationsBehabiour(){

    }

    public BarAgent getAgent(){
       return (BarAgent)myAgent;
    }
}
