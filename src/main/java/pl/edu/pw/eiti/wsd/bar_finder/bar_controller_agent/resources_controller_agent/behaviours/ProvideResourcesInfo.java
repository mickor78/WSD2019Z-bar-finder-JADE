package pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.resources_controller_agent.behaviours;

import java.io.IOException;
import java.io.Serializable;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.resources_controller_agent.ResourcesControllerAgent;

public class ProvideResourcesInfo extends CyclicBehaviour {

    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        ACLMessage reply;
        if (msg != null) {
            reply = msg.createReply();
            if (msg.getPerformative() == ACLMessage.QUERY_REF) {
                reply.setPerformative(ACLMessage.INFORM);
                try {
                    reply.setContentObject((Serializable)getAgent().checkResourcesInfo());
                } catch (IOException e) {
                    // TODO: Think about it.
                    e.printStackTrace();
                }
            } else {
                reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
            }
            myAgent.send(reply);
        } else {
            block();
        }
    }

    public ResourcesControllerAgent getAgent(){
        return (ResourcesControllerAgent)myAgent;
    }
}
