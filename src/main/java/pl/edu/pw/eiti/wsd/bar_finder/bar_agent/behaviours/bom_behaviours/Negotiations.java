package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import java.io.Serializable;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Bar;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Preferences;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.ProposalAcceptData;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JadeUtils.sendMessage;

public class Negotiations extends Behaviour {

    private NegotiationsSteps step = NegotiationsSteps.SEND_PROPOSAL;
    private String conversationId;
    private AID competitor;
    private Preferences customerPreferences;
    private double score;

    public Negotiations(String conversationId, AID competitor, Preferences customerPreferences) {
        super();
        this.conversationId = conversationId;
        this.competitor = competitor;
        this.customerPreferences = customerPreferences;
    }

    public Negotiations(String conversationId, AID competitor, Preferences customerPreferences, double score) {
        super();
        this.conversationId = conversationId;
        this.competitor = competitor;
        this.customerPreferences = customerPreferences;
        this.score = score;
    }

    @Override
    public void action() {
        switch (step) {
            case SEND_PROPOSAL:
                Bar offer = getAgent().getBar();
                // Opportunity to add some new business conditions.
                if (offer != null && offer.getBeers().size() > 0) {
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM, conversationId: %s) - sends proposal to %s.",
                        myAgent.getLocalName(), conversationId, competitor.getLocalName()));
                    sendMessage(myAgent, ACLMessage.PROPOSE, conversationId, competitor, score);
                    step = NegotiationsSteps.GET_PROPOSAL_ANSWER;
                }
                else {
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM, conversationId: %s) - sends refusal to %s.",
                        myAgent.getLocalName(), conversationId, competitor.getLocalName()));
                    sendMessage(myAgent, ACLMessage.REFUSE, conversationId, competitor, null);
                    step = NegotiationsSteps.DONE;
                }
                break;
            case GET_PROPOSAL_ANSWER:
                ACLMessage response = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchConversationId(conversationId),
                    MessageTemplate.MatchSender(competitor)));
                if (response != null) {
                    int performative = response.getPerformative();
                    if (performative == ACLMessage.ACCEPT_PROPOSAL) {
                        ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM, conversationId: %s) - receives proposal acceptation from %s.",
                            myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                        try {
                            Serializable contentResponse = response.getContentObject();
                            if (contentResponse instanceof ProposalAcceptData) {
                                ProposalAcceptData proposalAcceptData = (ProposalAcceptData)contentResponse;
                                AID customerId = proposalAcceptData.getCustomerAID();
                                List<AID> defeatedCompetitors = proposalAcceptData.getDefeatedCompetitors();
                                if (customerId != null) {
                                    BestOfferHolder boh = new BestOfferHolder(customerId, customerPreferences, score);
                                    if (defeatedCompetitors != null && defeatedCompetitors.size() > 0)
                                        boh.getDefeatedCompetitors().addAll(defeatedCompetitors);
                                    ConsolePrintingMsgUtils.PrintMsg(String.format(
                                        "%s (BOM, conversationId: %s) - sends negotiations end confirmation to %s.",
                                        myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                                    sendMessage(myAgent, ACLMessage.INFORM, conversationId, competitor, "Done");
                                    step = NegotiationsSteps.DONE;
                                }
                                else {
                                    // TODO: Failure? - this needs service implementation in BOH negotiations class
                                }
                            }
                            else {
                                // TODO: Not understand
                            }
                        } catch (UnreadableException e) {
                            // TODO:
                            e.printStackTrace();
                        }
                    }
                    else if (performative == ACLMessage.REJECT_PROPOSAL) {
                        ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM, conversationId: %s) - receives proposal rejection from %s.",
                            myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                        ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM, conversationId: %s) - sends negotiations end confirmation to %s.",
                            myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
                        sendMessage(myAgent, ACLMessage.INFORM, conversationId, competitor, "Done");
                        step = NegotiationsSteps.DONE;
                    }
                    else {
                        // TODO: Not understood
                    }
                }
                else {
                    block();
                }
                break;
        }
    }


    @Override
    public boolean done() {
        return step == NegotiationsSteps.DONE;
    }

    public BarAgent getAgent() {
        return (BarAgent) myAgent;
    }

    private BestOfferHolder getBOH() {
        return (BestOfferHolder) getParent();
    }

    enum NegotiationsSteps {
        SEND_PROPOSAL,
        GET_PROPOSAL_ANSWER,
        DONE
    }
}

