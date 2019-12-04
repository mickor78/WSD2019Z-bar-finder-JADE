package pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.loudness_controller_agent;

import jade.core.Agent;
import pl.edu.pw.eiti.wsd.bar_finder.BarFinderAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.loudness_controller_agent.behaviours.ProvideLoudnessLevel;

public class LoudnessControllerAgent extends BarFinderAgent {

    protected void setup() {
        System.out.println("Hello World! My name is "+getLocalName());
        addBehaviour(new ProvideLoudnessLevel());
    }

    public String checkLoudnessLevel(){
        return "4.20";
    }
}