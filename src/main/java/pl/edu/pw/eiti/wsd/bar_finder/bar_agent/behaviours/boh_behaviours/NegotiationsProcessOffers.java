package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;

import java.util.Collections;
import java.util.Map;
import java.util.NoSuchElementException;

public class NegotiationsProcessOffers extends OneShotBehaviour {
    @Override
    public void action() {
        try{
            Double bestCompetitorScore = Collections.max(getBOH().getCompetitorsScores().keySet());
            ParallelBehaviour responsesBehaviour = new ParallelBehaviour(ParallelBehaviour.WHEN_ALL);
            if(getBOH().getScore() > bestCompetitorScore){
                // BOH didn't lose
                getBOH().addSubBehaviour(new ProvideBestOffer());
            } else {
                // Winner
                String winnersConversationId = getBOH().getCompetitorsScores().get(bestCompetitorScore);
                AID winnersAID = getBOH().getCompetitors().get(winnersConversationId);
                getBOH().getCompetitors().remove(winnersConversationId);
                responsesBehaviour.addSubBehaviour(new NegotiationsSendResponse(winnersConversationId, winnersAID, true));
            }
            // Losers list
            for(Map.Entry<String, AID> loser : getBOH().getCompetitors().entrySet()){
                getBOH().getDefeatedCompetitors().add(loser.getValue());
                responsesBehaviour.addSubBehaviour(new NegotiationsSendResponse(loser.getKey(), loser.getValue(), false));
            }
            getBOH().addSubBehaviour(responsesBehaviour);
        } catch (NoSuchElementException e){
            // Competitors scores list is empty, send own offer
            getBOH().addSubBehaviour(new ProvideBestOffer());
        }

    }

    public BarAgent getAgent() {
        return (BarAgent) myAgent;
    }

    private BestOfferHolder getBOH() {
        return (BestOfferHolder) root();
    }
}
