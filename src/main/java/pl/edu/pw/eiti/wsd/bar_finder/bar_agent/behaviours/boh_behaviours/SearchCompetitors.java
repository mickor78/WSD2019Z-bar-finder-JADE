package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.boh_behaviours;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import jade.core.AID;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.GuidUtils.GetGuid;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JadeUtils.sendMessage;

public class SearchCompetitors extends Behaviour {

    private int attemptsCounter;

    public SearchCompetitors() { }

    @Override
    public void action() {
        List<AID> competitors = getCompetitors();
        BestOfferHolder boh = getBOH();
        if (competitors != null && competitors.size() > 0) {
            competitors.forEach(c -> {
                String conversationId = GetGuid();
                boh.getCompetitors().put(conversationId, c);
                getParentBehaviour().addSubBehaviour(new NegotiationsGetOffer(conversationId, c));
            });

            HashMap<String, AID> receivers = boh.getCompetitors();
            BarAgent barAgent = getAgent();
            ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH) - sends CFP messages to %d competitors.",
                myAgent.getLocalName(), receivers.size()));
            sendMessage(myAgent, ACLMessage.CFP, receivers, barAgent.getCodec(),
                barAgent.getPreferencesOntology(), getBOH().getCustomerPreferences());
        }

        attemptsCounter++;
    }

    @Override
    public boolean done() {
        return getBOH().getCompetitors().size() > 0 || attemptsCounter == 3;
    }

    public BarAgent getAgent() {
        return (BarAgent) myAgent;
    }

    private List<AID> getCompetitors() {
        List<AID> result = new LinkedList<>(getAgent().getNearbyBars());
        if (getBOH().getDefeatedCompetitors() != null) {
            result.removeAll(getBOH().getDefeatedCompetitors());
        }
        return result;
    }

    private BestOfferHolder getBOH() {
        return (BestOfferHolder)root();
    }

    private StartNegotiations getParentBehaviour() {
        return (StartNegotiations)getParent();

    }
}
