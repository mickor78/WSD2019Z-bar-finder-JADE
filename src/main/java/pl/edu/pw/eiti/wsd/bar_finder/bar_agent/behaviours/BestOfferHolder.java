package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours;

import jade.core.AID;

import jade.core.behaviours.SequentialBehaviour;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours.ProvideBestOffer;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours.StartNegotiations;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesDictionary;

import java.util.List;

public class BestOfferHolder extends SequentialBehaviour {

    private AID customerAID;
    private PreferencesDictionary customerPreferences;
    private List<AID> negotiationHistory;

    public AID getCustomerAID() {
        return customerAID;
    }

    public PreferencesDictionary getCustomerPreferences() {
        return customerPreferences;
    }

    public List<AID> getNegotiationHistory() {
        return negotiationHistory;
    }

    public BestOfferHolder(AID customerAID, PreferencesDictionary customerPreferences)
    {
        super();

        this.customerAID = customerAID;
        this.customerPreferences = customerPreferences;

        addSubBehaviour(new StartNegotiations());
        // after all negotiations ended and no one took BOH
        addSubBehaviour(new ProvideBestOffer());
    }

}
