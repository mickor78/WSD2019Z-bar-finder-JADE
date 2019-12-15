package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.ProposalAcceptData;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;


import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JadeUtils.sendMessage;

public class NegotiationsSendResponse extends Behaviour {

    private NegotiationsSteps step = NegotiationsSteps.SEND_RESPONSE;
    private String conversationId;
    private AID competitor;
    private boolean isWin = false;

    public NegotiationsSendResponse(String conversationId, AID competitor, boolean isWin) {
        super();
        this.conversationId = conversationId;
        this.competitor = competitor;
        this.isWin = isWin;
    }

    public void action() {
        ACLMessage response;
        switch (step) {
            case SEND_RESPONSE:

                if(isWin){
                    // Competitor won
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - is defeated by %s.",
                            myAgent.getLocalName(), conversationId, competitor.getLocalName()));
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - sends proposal acceptation to %s.",
                            myAgent.getLocalName(), conversationId, competitor.getLocalName()));
                    sendMessage(myAgent, ACLMessage.ACCEPT_PROPOSAL, conversationId, competitor,
                            (new ProposalAcceptData(getBOH().getCustomerAID(), getBOH().getDefeatedCompetitors())));
                } else {
                    // Competitor lost
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - wins against %s.",
                            myAgent.getLocalName(), conversationId, competitor.getLocalName()));
                    isWin = true;
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - sends proposal rejection to %s.",
                            myAgent.getLocalName(), conversationId, competitor.getLocalName()));
                    sendMessage(myAgent, ACLMessage.REJECT_PROPOSAL, conversationId, competitor,
                            null);
                }
                step = NegotiationsSteps.GET_CONFIRM;
                break;
            case GET_CONFIRM:
                response = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchConversationId(conversationId),
                        MessageTemplate.MatchSender(competitor)));
                if (response != null) {
                    int performative = response.getPerformative();
                    if (performative == ACLMessage.INFORM) {
                        String content = response.getContent();
                        if (content.equals("Done")) {
                            ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH, conversationId: %s) - receives negotiations end confirmation " +
                                    " from %s.", myAgent.getLocalName(), conversationId, response.getSender().getLocalName()));
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
        SEND_RESPONSE,
        GET_CONFIRM,
        DONE
    }
}