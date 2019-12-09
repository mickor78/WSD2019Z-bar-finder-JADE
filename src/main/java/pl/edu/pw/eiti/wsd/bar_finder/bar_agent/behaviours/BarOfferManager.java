package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours;

import jade.core.behaviours.ParallelBehaviour;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours.AwaitNegotiations;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours.AwaitPreferences;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours.GetBarParameters;

public class BarOfferManager extends ParallelBehaviour {

    public BarOfferManager(){
        super(ParallelBehaviour.WHEN_ALL);
        this.addSubBehaviour(new GetBarParameters(myAgent, 10000));
        this.addSubBehaviour(new AwaitPreferences());
        this.addSubBehaviour(new AwaitNegotiations());
    }

    public BarAgent getAgent(){
       return (BarAgent)myAgent;
    }
}
