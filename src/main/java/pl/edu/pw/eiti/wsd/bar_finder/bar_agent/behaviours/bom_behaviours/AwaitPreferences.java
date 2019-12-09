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
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.commons.mappers.PreferencesMapper.Map;

public class AwaitPreferences extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive(MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.INFORM),
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
                            ConsolePrintingMsgUtils.PrintMsg(myAgent.getLocalName() + " - received from " + msg.getSender().getLocalName()
                                    + " :\n" + customerPreferences.toString());
                            myAgent.addBehaviour(new BestOfferHolder(customerAID, Map(customerPreferences)));
                        } else {
                            // TODO
                            ConsolePrintingMsgUtils.PrintMsg(myAgent.getLocalName() + " - received from " + msg.getSender().getLocalName()
                                    + " : empty customer preferences list");
                        }
                    } else {
                        // TODO
                        ConsolePrintingMsgUtils.PrintMsg(myAgent.getLocalName() + " - received from " + msg.getSender().getLocalName()
                                + " : wrong data");
                    }
                } else {
                    // TODO
                    ConsolePrintingMsgUtils.PrintMsg(myAgent.getLocalName() + " - received from " + msg.getSender().getLocalName()
                            + " : wrong data");
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
