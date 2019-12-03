package pl.edu.pw.eiti.wsd.bar_finder;

import jade.core.Agent;
import jade.core.AID;

import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;

public class BarFinderAgent extends Agent {


    protected void register( ServiceDescription sd) {
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());

        try {
            DFAgentDescription list[] = DFService.search( this, dfd );
            if ( list.length>0 )
                DFService.deregister(this);

            dfd.addServices(sd);
            DFService.register(this,dfd);
        }catch (FIPAException fe) {
            fe.printStackTrace();
        }
    }

    protected AID getService( String service ) {
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType( service );
        dfd.addServices(sd);
        try{
            DFAgentDescription[] result = DFService.search(this, dfd);
            if (result.length>0)
                return result[0].getName() ;
        }catch (FIPAException fe) {
            fe.printStackTrace();
        }
        return null;
    }

    protected AID[] searchDF(String service ){
        DFAgentDescription dfd = new DFAgentDescription();
        ServiceDescription sd = new ServiceDescription();
        sd.setType( service );
        dfd.addServices(sd);

        SearchConstraints ALL = new SearchConstraints();
        ALL.setMaxResults(new Long(-1));

        try{
            DFAgentDescription[] result = DFService.search(this, dfd, ALL);
            AID[] agents = new AID[result.length];
            for (int i=0; i<result.length; i++)
                agents[i] = result[i].getName() ;
            return agents;

        }catch (FIPAException fe){
            fe.printStackTrace();
        }
        return null;
    }

}
