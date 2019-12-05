package pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.loudness_controller_agent;

import java.util.Random;

import pl.edu.pw.eiti.wsd.bar_finder.BarFinderAgent;
import pl.edu.pw.eiti.wsd.bar_finder.bar_controller_agent.loudness_controller_agent.behaviours.ProvideLoudnessLevel;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Bar;

public class LoudnessControllerAgent extends BarFinderAgent {

    protected void setup(){
        System.out.println("Hello World! My name is " + getLocalName());

        addBehaviour(new ProvideLoudnessLevel());
    }

    public Bar.LoudnessLevel checkLoudnessLevel(){
        Random random = new Random();

        Bar.LoudnessLevel[] values = Bar.LoudnessLevel.values();
        int idx = random.nextInt(values.length - 1) + 1;

        return values[idx];
    }
}