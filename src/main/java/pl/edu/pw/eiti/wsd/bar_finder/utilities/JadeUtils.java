package pl.edu.pw.eiti.wsd.bar_finder.utilities;

import java.util.List;

import jade.content.Concept;
import jade.content.lang.Codec;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.onto.basic.Action;
import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public final class JadeUtils {

    public static void sendMessage(Agent agent, int performative, Codec codec, Ontology ontology,
                                   Concept content, List<AID> receivers) {
        if (agent != null && performative >= 0 && codec != null && ontology != null &&
            content != null && receivers != null && !receivers.isEmpty()) {
            receivers.forEach(r -> {
                try {
                    ACLMessage msg = new ACLMessage(performative);
                    msg.setLanguage(codec.getName());
                    msg.setOntology(ontology.getName());
                    msg.addReceiver(r);
                    agent.getContentManager().fillContent(msg, new Action(r, content));
                    agent.send(msg);
                } catch (Codec.CodecException | OntologyException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
