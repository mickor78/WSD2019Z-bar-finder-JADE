package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours;

import jade.core.AID;
import jade.core.behaviours.ParallelBehaviour;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;

import java.util.ArrayList;
import java.util.List;


public class StartNegotiations extends ParallelBehaviour {

    public StartNegotiations(){
        super(ParallelBehaviour.WHEN_ALL);
        this.addSubBehaviour(new SearchCompetitors());
    }

    public BarAgent getAgent(){
        return (BarAgent)myAgent;
    }

    public List<AID> getNearbyBars(){
        List<AID> result = new ArrayList<>(getAgent().getNearbyBars());
        if(getBOH().getNegotiationHistory() != null) {
            result.removeAll(getBOH().getNegotiationHistory());
        }
        return result;
    }

    public BestOfferHolder getBOH(){
        return (BestOfferHolder)getParent();
    }
}
