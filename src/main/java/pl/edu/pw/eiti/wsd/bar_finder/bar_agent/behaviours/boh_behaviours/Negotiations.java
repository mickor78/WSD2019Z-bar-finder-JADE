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

public class Negotiations extends Behaviour {

    private NegotiationsSteps step = NegotiationsSteps.GET_PROPOSAL_AND_COMPARE;
    private String conversationId;
    private AID competitor;
    private boolean isWin = false;

    public Negotiations(String conversationId, AID competitor) {
        super();
        this.conversationId = conversationId;
        this.competitor = competitor;
    }

    public void action() {
        ACLMessage response;
        switch (step) {
            case GET_PROPOSAL_AND_COMPARE:
                response = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchConversationId(conversationId),
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
                               if (getBOH().IsBetterOffer(competitorScore)) {
                                   // Competitor wins
                                   ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - is defeated by %s.",
                                       myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                                   isWin = false;
                                   // Competitor loses
                                   ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - sends proposal acceptation to %s.",
                                       myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                                   sendMessage(myAgent, ACLMessage.ACCEPT_PROPOSAL, conversationId, competitor,
                                       (new ProposalAcceptData(getBOH().getCustomerAID(), getBOH().getDefeatedCompetitors())));
                               }
                               else {
                                   // Competitor loses
                                   ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - wins against %s.",
                                       myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                                   isWin = true;
                                   ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - sends proposal rejection to %s.",
                                       myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                                   sendMessage(myAgent, ACLMessage.REJECT_PROPOSAL, conversationId, competitor,
                                       null);
                               }
                               step = NegotiationsSteps.GET_CONFIRM;
                           }
                           else {
                               // TODO: Not understand
                           }
                       } catch (UnreadableException e) {
                           // TODO:
                           e.printStackTrace();
                       }
                   }
                   else if (performative == ACLMessage.REFUSE) {
                       ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - receives refusal from %s.",
                           myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                       getBOH().getDefeatedCompetitors().add(competitor);
                       step = NegotiationsSteps.DONE;
                   }
                   else {
                       // TODO: Not understood
                   }
                } else {
                    block();
                }
                break;
            case GET_CONFIRM:
                response = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchConversationId(conversationId),
                    MessageTemplate.MatchSender(competitor)));
                if (response != null) {
                    int performative = response.getPerformative();
                    if (isWin) {
                        if (performative == ACLMessage.INFORM) {
                            String content = response.getContent();
                            if (content.equals("Done")) {
                                ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - receives negotiations end confirmation " +
                                    " from %s.", myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                                getBOH().getDefeatedCompetitors().add(competitor);
                                step = NegotiationsSteps.DONE;
                            }
                            else {
                                // TODO: Not understood
                            }
                        }
                        else {
                            // TODO: Not understood
                        }
                    }
                    else  {
                        step = NegotiationsSteps.DONE;
                        // TODO: Jak zakończyć inne negocjacje i czy je przerywać?
                    }
                }
                else {
                    block();
                }
                break;
        }
    }

    public boolean done() {
        return step == NegotiationsSteps.DONE;
    }

    public BarAgent getAgent() {
        return (BarAgent) myAgent;
    }

    private BestOfferHolder getBOH() {
        return (BestOfferHolder) root();
    }

    enum NegotiationsSteps {
        GET_PROPOSAL_AND_COMPARE,
        GET_CONFIRM,
        DONE
    }
}