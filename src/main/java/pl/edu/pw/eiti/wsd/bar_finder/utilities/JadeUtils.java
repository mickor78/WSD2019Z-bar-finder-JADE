package pl.edu.pw.eiti.wsd.bar_finder.utilities;

import java.io.IOException;
import java.io.Serializable;
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

    public static void sendMessage(Agent agent, int performative, List<AID> receivers, Codec codec, Ontology ontology,
                                   Concept content) {
        if (agent != null && performative >= 0 && receivers != null && !receivers.isEmpty()) {
            receivers.forEach(r -> {
                try {
                    ACLMessage msg = new ACLMessage(performative);
                    msg.addReceiver(r);
                    if (codec != null)
                        msg.setLanguage(codec.getName());
                    if (ontology != null)
                        msg.setOntology(ontology.getName());
                    if (content != null)
                        agent.getContentManager().fillContent(msg, new Action(r, content));
                    agent.send(msg);
                } catch (Codec.CodecException | OntologyException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static void sendMessage(Agent agent, int performative, List<AID> receivers, String content) {
        if (agent != null && performative >= 0 && receivers != null && !receivers.isEmpty()) {
            receivers.forEach(r -> {
                ACLMessage msg = new ACLMessage(performative);
                msg.addReceiver(r);
                if (content != null && !content.isEmpty())
                    msg.setContent(content);
                agent.send(msg);
            });
        }
    }

    public static void sendMessage(Agent agent, int performative, List<AID> receivers, Serializable contentObj) {
        if (agent != null && performative >= 0 && receivers != null && !receivers.isEmpty()) {
            receivers.forEach(r -> {
                try {
                    ACLMessage msg = new ACLMessage(performative);
                    msg.addReceiver(r);
                    if (contentObj != null)
                        msg.setContentObject(contentObj);
                    agent.send(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
