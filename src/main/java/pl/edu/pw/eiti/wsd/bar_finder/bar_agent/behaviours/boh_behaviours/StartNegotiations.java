package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours;

import jade.core.behaviours.ParallelBehaviour;

public class StartNegotiations extends ParallelBehaviour {

    public StartNegotiations() {
        super(ParallelBehaviour.WHEN_ALL);
        addSubBehaviour(new SearchCompetitors());
    }
}
