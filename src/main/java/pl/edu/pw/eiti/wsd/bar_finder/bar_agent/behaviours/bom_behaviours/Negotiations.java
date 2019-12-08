package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;


import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT_ROLE_BOH;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT_ROLE_BOM;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JadeUtils.sendMessage;

public class Negotiations extends Behaviour {

    // State names
    private static final int SEND_PREFERENCES_STATE = 0;
    private static final int AWAIT_PREFERENCES_STATE = 1;
    private static final int SEND_OFFER_STATE = 2;
    private static final int AWAIT_OFFER_STATE = 3;
    private static final int PASS_BOH_STATE = 4;
    private static final int END_NEGOTIATIONS = 5;

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
            state = SEND_PREFERENCES_STATE;
        } else if (role.equals(BAR_AGENT_ROLE_BOM)) {
            state = AWAIT_PREFERENCES_STATE;
        } else {
            state = END_NEGOTIATIONS;
            // end
        }
    }


    @Override
    public void action() {
        switch (state) {
            case SEND_PREFERENCES_STATE:
                //BOH - send client preferences
                break;
            case AWAIT_PREFERENCES_STATE:
                //BOM - receive client preferences
                break;
            case SEND_OFFER_STATE:
                // update own score
                // if competitor can be beaten (ownScore > competitorsScore), prepare counteroffer and send
                // state = AWAIT_OFFER_STATE

                // if not
                // send admit defeat message
                // if (BOH) state = PASS_BOH_STATE
                // else state = END_NEGOTIATIONS
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

