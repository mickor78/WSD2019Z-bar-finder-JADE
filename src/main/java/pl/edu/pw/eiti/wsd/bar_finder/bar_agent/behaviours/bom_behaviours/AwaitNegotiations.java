package pl.edu.pw.eiti.wsd.bar_finder.bar_agent.behaviours.bom_behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesDictionary;

public class AwaitNegotiations extends CyclicBehaviour {
    public void action() {

        ACLMessage msg = myAgent.receive();

        try {
            if (msg != null) {
                System.out.println(myAgent.getLocalName()+" - received: "+ msg.getContent());
                //tmp
                PreferencesDictionary preferences = (PreferencesDictionary) msg.getContentObject();
                //add negotiations behaviour
            } else {
                block();
            }
        }
        catch (UnreadableException e) {
            System.out.println(myAgent.getLocalName() + ": not understood.");
            ACLMessage reply = msg.createReply();
            reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
            reply.setContent("co");
            myAgent.send(reply);
        }
    }
}
