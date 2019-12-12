package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;

import java.io.Serializable;
import java.util.List;

import jade.core.AID;

public class ProposalAcceptData implements Serializable {

    private AID customerAID;
    private List<AID> defeatedCompetitors;

    public ProposalAcceptData(AID customerAID, List<AID> defeatedCompetitors) {
        setCustomerAID(customerAID);
        setDefeatedCompetitors(defeatedCompetitors);
    }

    public AID getCustomerAID() {
        return customerAID;
    }
    public void setCustomerAID(AID customerAID) {
        this.customerAID = customerAID;
    }

    public List<AID> getDefeatedCompetitors() {
        return defeatedCompetitors;
    }
    public void setDefeatedCompetitors(List<AID> defeatedCompetitors) {
        this.defeatedCompetitors = defeatedCompetitors;
    }
}
