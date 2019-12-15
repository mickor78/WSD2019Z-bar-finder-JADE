package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours;

import java.io.Serializable;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.ProposalAcceptData;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JadeUtils.sendMessage;

public class NegotiationsGetOffer extends Behaviour {

    private String conversationId;
    private AID competitor;
    private boolean awaitOffer = true;

    public NegotiationsGetOffer(String conversationId, AID competitor) {
        super();
        this.conversationId = conversationId;
        this.competitor = competitor;
    }

    public void action() {
        ACLMessage response = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchConversationId(conversationId),
                MessageTemplate.MatchSender(competitor)));
        if (response != null) {
            int performative = response.getPerformative();
            if (performative == ACLMessage.PROPOSE) {
                try {
                    Serializable contentResponse = response.getContentObject();
                    if (contentResponse instanceof Double) {
                        double competitorScore = (Double) contentResponse;
                        ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - receives proposal (score: %f) from %s.",
                                myAgent.getLocalName(), conversationId, competitorScore, response.getSender().getLocalName()));
                        getBOH().getCompetitorsScores().put(competitorScore, conversationId);
                        awaitOffer = false;
                    } else {
                        // TODO: Not understood
                    }
                } catch (UnreadableException e) {
                    // TODO:
                    e.printStackTrace();
                }
            } else if (performative == ACLMessage.REFUSE) {
                ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - receives refusal from %s.",
                        myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                getBOH().getDefeatedCompetitors().add(competitor);
                awaitOffer = false;
            } else {
                // TODO: Not understood
            }
        } else {
            block();
        }
    }

    public boolean done() {
        return !awaitOffer;
    }

    public BarAgent getAgent() {
        return (BarAgent) myAgent;
    }

    private BestOfferHolder getBOH() {
        return (BestOfferHolder) root();
    }

}