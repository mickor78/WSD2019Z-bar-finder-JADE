package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.BarOfferManager;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Preferences;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesDictionary;
import pl.edu.pw.eiti.wsd.bar_finder.commons.score.ParametersScore;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.commons.mappers.PreferencesMapper.Map;

public class AwaitNegotiations extends CyclicBehaviour {

    public void action() {
        ACLMessage msg = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.CFP),
                MessageTemplate.and(MessageTemplate.MatchLanguage(getAgent().getCodec().getName()),
                    MessageTemplate.MatchOntology(getAgent().getPreferencesOntology().getName()))));

        if (msg != null) {
            AID customerAID = msg.getSender();
            String conversationId = msg.getConversationId();
            try {
                ContentElement receivedContent = myAgent.getContentManager().extractContent(msg);

                if (customerAID != null && receivedContent instanceof Action) {
                    Action receivedAction = (Action) receivedContent;
                    Concept concept = receivedAction.getAction();
                    if (concept instanceof Preferences) {
                        Preferences customerPreferences = (Preferences) concept;
                        if (!customerPreferences.getPreferencesParameters().isEmpty()) {
                            ConsolePrintingMsgUtils.PrintMsg(myAgent.getLocalName() + " (BOM, conversationId: " + conversationId +
                                ") - receives from " + msg.getSender().getLocalName() + ":\n" + customerPreferences.toString());
                            double score = 0.0;
                            // TODO: Do zastanowienia, czy lepiej mapowanie i dict, czy bez mapowania, ale sprawdzanie ciągłe,
                            //  czy dany parametr należy do zdefiniowanych parametrów.
                            PreferencesDictionary preferencesDictionary = Map(customerPreferences);
                            if (preferencesDictionary != null) {
                                // TODO: ParametersScore.score() jako metoda statyczna
                                score = new ParametersScore().score(preferencesDictionary, getAgent().getBar());
                                ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM, conversationId: %s) - score for preferences: %f.",
                                        myAgent.getLocalName(), conversationId, score));
                            }
                            getBOM().addSubBehaviour(new Negotiations(conversationId, msg.getSender(),
                                customerPreferences, score));
                        }
                        else {
                            // TODO
                            ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM, conversationId: %s) - receives empty customer preferences list from %s.",
                                myAgent.getLocalName(), conversationId, msg.getSender().getLocalName()));
                        }
                    }
                    else {
                        // TODO
                        ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM, conversationId: %s) - receives wrong data from %s.",
                            myAgent.getLocalName(), conversationId, msg.getSender().getLocalName()));
                    }
                }
                else {
                    // TODO
                    ConsolePrintingMsgUtils.PrintMsg(String.format("%s (BOM, conversationId: %s) - receives wrong data from %s.",
                        myAgent.getLocalName(), conversationId, msg.getSender().getLocalName()));
                }
            }
            catch (Codec.CodecException e) {
                e.printStackTrace();
            } catch (OntologyException e) {
                e.printStackTrace();
            }
        }
        else {
            block();
        }
    }

    public BarAgent getAgent() {
        return (BarAgent)super.getAgent();
    }

    private BarOfferManager getBOM() {
        return (BarOfferManager) getParent();
    }
}
