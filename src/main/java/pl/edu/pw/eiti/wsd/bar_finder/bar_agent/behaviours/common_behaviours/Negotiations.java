package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.common_behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.lang.acl.UnreadableException;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Bar;
import pl.edu.pw.eiti.wsd.bar_finder.commons.score.ParametersScore;

import java.io.IOException;
import java.util.Objects;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT_ROLE_BOH;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT_ROLE_BOM;

public class Negotiations extends Behaviour {

    // State names
    private static final int RECEIVE_AND_COMPARE_OFFER = 0;
    private static final int SEND_OFFER_STATE = 1;
    private static final int AWAIT_OFFER_STATE = 2;
    private static final int PASS_BOH_STATE = 3;
    private static final int END_NEGOTIATIONS = 4;

    private String role;
    private String conversationId;
    private AID competitor;

    private int state;
    private double ownScore;
    private double competitorsScore;

    public Negotiations(String role, String conversationId, AID competitor) {
        this.role = role;
        this.conversationId = conversationId;
        this.competitor = competitor;

        System.out.println(conversationId);
        if (role.equals(BAR_AGENT_ROLE_BOH)) {
            state = RECEIVE_AND_COMPARE_OFFER;
        } else if (role.equals(BAR_AGENT_ROLE_BOM)) {
            state = SEND_OFFER_STATE;
        } else {
            state = END_NEGOTIATIONS;
            // end
        }
    }

    @Override
    public void action() {
        switch (state) {
            case RECEIVE_AND_COMPARE_OFFER:
                //BOH - send client preferences
                ACLMessage competitorProposal = myAgent.receive(MessageTemplate.MatchSender(competitor));
                if (Objects.nonNull(competitorProposal) && competitorProposal.getPerformative() == ACLMessage.PROPOSE) {
                    Bar competitorOffer;
                    try {
                        competitorOffer = (Bar) competitorProposal.getContentObject();
                        //TODO log
                        ownScore = new ParametersScore().score(getBOH().getCustomerPreferences(), getAgent().getBar());
                        competitorsScore = new ParametersScore().score(getBOH().getCustomerPreferences(), competitorOffer);

                        if (ownScore < competitorsScore)
                            state = PASS_BOH_STATE;
                        else {
                            ACLMessage rejectResponse = new ACLMessage(ACLMessage.REJECT_PROPOSAL);
                            rejectResponse.addReceiver(competitor);
                            myAgent.send(rejectResponse);
                            //TODO
                        }
                    } catch (UnreadableException e) {
                        //TODO logging, how to handle it?
                        e.printStackTrace();
                    }
                } else state = END_NEGOTIATIONS;
                break;
            case SEND_OFFER_STATE:
                //BOM - send bar object as an offer
                ACLMessage proposalOffer = new ACLMessage(ACLMessage.PROPOSE);
                proposalOffer.addReceiver(competitor);

                try {
                    proposalOffer.setContentObject(getAgent().getBar());
                } catch (IOException e) {
                    //TODO logging, how to handle it?
                    e.printStackTrace();
                }
                break;
            case AWAIT_OFFER_STATE:
                // receive offer
                // update competitors score
                // state = SEND_OFFER_STATE

                // receive defeat admission
                // state = END_NEGOTIATIONS

                // receive failure
                // probably BOH changed
                // state = END_NEGOTIATIONS


                break;
            case PASS_BOH_STATE:
                // stop all active negotiations
                // myAgent.addBehaviour(new SetNewBestOffer)
                // stop BOH behaviour without providing best offer
                // state = END_NEGOTIATIONS
                break;
            case END_NEGOTIATIONS:
                // add to negotiationHistory if BOH
                break;

        }
    }


    @Override
    public boolean done() {
        return state == END_NEGOTIATIONS;
    }

    public BarAgent getAgent() {
        return (BarAgent) myAgent;
    }

    public BestOfferHolder getBOH() {
        return (BestOfferHolder) root();
    }

}

