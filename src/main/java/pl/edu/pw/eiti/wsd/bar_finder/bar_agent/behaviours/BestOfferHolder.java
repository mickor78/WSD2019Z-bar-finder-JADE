package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours;

import jade.core.AID;
import jade.core.behaviours.Behaviour;

import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesDictionary;

public class BestOfferHolder extends Behaviour {

    private AID customerAID;
    private PreferencesDictionary customerPreferences;

    public AID getCustomerAID() {
        return customerAID;
    }

    public PreferencesDictionary getCustomerPreferences() {
        return customerPreferences;
    }

    public BestOfferHolder(AID customerAID, PreferencesDictionary customerPreferences)
    {
        super();

        this.customerAID = customerAID;
        this.customerPreferences = customerPreferences;
    }

    public void action() {

    }

    public boolean done() {
        return true;
    }
}
