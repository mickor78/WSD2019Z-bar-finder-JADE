package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.onto.OntologyException;
import jade.content.onto.UngroundedException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BestOfferHolder;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Preferences;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesDictionary;
import pl.edu.pw.eiti.wsd.bar_finder.commons.score.ParametersScore;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.commons.mappers.PreferencesMapper.Map;

public class AwaitPreferences extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.REQUEST),
                MessageTemplate.and(MessageTemplate.MatchLanguage(getAgent().getCodec().getName()),
                    MessageTemplate.MatchOntology(getAgent().getPreferencesOntology().getName()))));
        try {
            if (msg != null) {
                AID customerAID = msg.getSender();
                ContentElement receivedContent = myAgent.getContentManager().extractContent(msg);
                if (customerAID != null && receivedContent instanceof Action) {
                    Action receivedAction = (Action) receivedContent;
                    Concept concept = receivedAction.getAction();
                    if (concept instanceof Preferences) {
                        Preferences customerPreferences = (Preferences) concept;
                        if (!customerPreferences.getPreferencesParameters().isEmpty()) {
                            ConsolePrintingMsgUtils.PrintMsg(myAgent.getLocalName() + " (BOM) - receives from " + msg.getSender().getLocalName()
                                + ":\n" + customerPreferences.toString());
                            double score = 0.0;
                            // TODO: Without mapping?
                            PreferencesDictionary preferencesDictionary = Map(customerPreferences);
                            if (preferencesDictionary != null) {
                                // TODO: ParametersScore - static?
                                score = new ParametersScore().score(preferencesDictionary, getAgent().getBar());
                                ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOH) - score for customer %s: %f.",
                                        myAgent.getLocalName(), customerAID.getLocalName(), score));
                            }
                            myAgent.addBehaviour(new BestOfferHolder(customerAID, customerPreferences, score));
                        } else {
                            // TODO
                            ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM) - receives empty customer preferences list from %s.",
                                myAgent.getLocalName(), msg.getSender().getLocalName()));
                        }
                    } else {
                        // TODO
                        ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM) - receives wrong data from %s.",
                            myAgent.getLocalName(), msg.getSender().getLocalName()));
                    }
                } else {
                    // TODO
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM) - receives wrong data from %s.",
                        myAgent.getLocalName(), msg.getSender().getLocalName()));
                }
            } else {
                block();
            }
        } catch (UngroundedException e) {
            e.printStackTrace();
        } catch (OntologyException e) {
            e.printStackTrace();
        } catch (Codec.CodecException e) {
            e.printStackTrace();
        }
    }

    public BarAgent getAgent() {
        return (BarAgent) myAgent;
    }

}
