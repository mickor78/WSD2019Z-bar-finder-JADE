package pl.edu.pw.eiti.wsd.bar_finder.utilities;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
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
            receivers.forEach(r ->
                sendMessage(agent, performative, null, r, codec, ontology, content)
            );
        }
    }

    public static void sendMessage(Agent agent, int performative, HashMap<String, AID> receivers, Codec codec, Ontology ontology,
                                   Concept content) {
        if (agent != null && performative >= 0 && receivers != null && !receivers.isEmpty()) {
            receivers.forEach((conversationId, receiverId) ->
                sendMessage(agent, performative, conversationId, receiverId, codec, ontology, content)
            );
        }
    }

    public static void sendMessage(Agent agent, int performative, String conversationId, AID receiverId, Codec codec,
                                   Ontology ontology, Concept content) {
        if (agent != null && performative >= 0 && receiverId != null) {
            try {
                ACLMessage msg = new ACLMessage(performative);
                msg.addReceiver(receiverId);
                if (conversationId != null && !conversationId.isEmpty())
                    msg.setConversationId(conversationId);
                if (codec != null)
                    msg.setLanguage(codec.getName());
                if (ontology != null)
                    msg.setOntology(ontology.getName());
                if (content != null)
                    agent.getContentManager().fillContent(msg, new Action(receiverId, content));
                agent.send(msg);
            } catch (Codec.CodecException | OntologyException e) {
                e.printStackTrace();
            }
        }
    }

    public static void sendMessage(Agent agent, int performative, List<AID> receivers, String content) {
        if (agent != null && performative >= 0 && receivers != null && !receivers.isEmpty()) {
            receivers.forEach(r ->
                sendMessage(agent, performative, null, r, content)
            );
        }
    }

    public static void sendMessage(Agent agent, int performative, HashMap<String, AID> receivers, String content) {
        if (agent != null && performative >= 0 && receivers != null && !receivers.isEmpty()) {
            receivers.forEach((conversationId, receiverId) ->
                sendMessage(agent, performative, conversationId, receiverId, content)
            );
        }
    }

    public static void sendMessage(Agent agent, int performative, String conversationId, AID receiverId,
                                   String content) {
        if (agent != null && performative >= 0 && receiverId != null) {
            ACLMessage msg = new ACLMessage(performative);
            msg.addReceiver(receiverId);
            if (conversationId != null && !conversationId.isEmpty())
                msg.setConversationId(conversationId);
            if (content != null && !content.isEmpty())
                msg.setContent(content);
            agent.send(msg);
        }
    }

    public static void sendMessage(Agent agent, int performative, List<AID> receivers, Serializable contentObj) {
        if (agent != null && performative >= 0 && receivers != null && !receivers.isEmpty()) {
            receivers.forEach(r ->
                sendMessage(agent, performative, null, r, contentObj)
            );
        }
    }

    public static void sendMessage(Agent agent, int performative, HashMap<String, AID> receivers, Serializable contentObj) {
        if (agent != null && performative >= 0 && receivers != null && !receivers.isEmpty()) {
            receivers.forEach((conversationId, receiverId) ->
                sendMessage(agent, performative, conversationId, receiverId, contentObj)
            );
        }
    }

    public static void sendMessage(Agent agent, int performative, String conversationId, AID receiverId,
                                   Serializable contentObj) {
        if (agent != null && performative >= 0 && receiverId != null) {
            try {
                ACLMessage msg = new ACLMessage(performative);
                msg.addReceiver(receiverId);
                if (conversationId != null && !conversationId.isEmpty())
                    msg.setConversationId(conversationId);
                if (contentObj != null)
                    msg.setContentObject(contentObj);
                agent.send(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
