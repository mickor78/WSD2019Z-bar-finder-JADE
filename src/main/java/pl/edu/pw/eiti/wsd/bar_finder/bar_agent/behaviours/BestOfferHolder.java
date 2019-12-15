package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.SequentialBehaviour;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours.NegotiationsProcessOffers;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours.ProvideBestOffer;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours.StartNegotiations;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours.Negotiations;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Preferences;

public class BestOfferHolder extends SequentialBehaviour {

    private AID customerAID;
    private Preferences customerPreferences;
    private double score;
    // HashTable - synchronized, HashMap - unsynchronized, but better performance
    // Key - conversation Id, value - bar id.
    private HashMap<String, AID> competitors;
    private Hashtable<Double, String> competitorsScores;
    private List<AID> defeatedCompetitors;

    public BestOfferHolder(AID customerAID, Preferences customerPreferences) {
        super();

        this.customerAID = customerAID;
        this.customerPreferences = customerPreferences;
        this.competitors = new HashMap<>();
        this.competitorsScores = new Hashtable<>();
        this.defeatedCompetitors = new LinkedList<>();

        addSubBehaviour(new StartNegotiations());
        // after all negotiations ended and no one took BOH
        addSubBehaviour(new ProvideBestOffer());
    }

    public BestOfferHolder(AID customerAID, Preferences customerPreferences, Double score) {
        super();

        this.customerAID = customerAID;
        this.customerPreferences = customerPreferences;
        this.competitors = new HashMap<>();
        this.defeatedCompetitors = new LinkedList<>();
        this.competitorsScores = new Hashtable<>();
        this.score = score;

        addSubBehaviour(new StartNegotiations());
        // after all negotiations ended and no one took BOH
        addSubBehaviour(new NegotiationsProcessOffers());
    }

    public AID getCustomerAID() {
        return customerAID;
    }

    public Preferences getCustomerPreferences() {
        return customerPreferences;
    }

    public double getScore() {
        return score;
    }

    public HashMap<String, AID> getCompetitors() {
        return competitors;
    }

    public List<AID> getDefeatedCompetitors() {
        return defeatedCompetitors;
    }

    public Hashtable<Double, String> getCompetitorsScores() {
        return competitorsScores;
    }

    public boolean IsBetterOffer(double competitorScore) {
        return competitorScore > score;
    }

    public BarAgent getAgent() {
        return (BarAgent) myAgent;
    }
}
