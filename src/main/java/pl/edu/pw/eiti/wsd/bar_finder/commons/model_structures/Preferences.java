package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;

import jade.content.Concept;

import java.util.LinkedList;
import java.util.List;

public class Preferences implements Concept {

    private List<PreferencesParameter> preferencesParameters;

    public Preferences() {
        preferencesParameters = new LinkedList<>();
    }

    public List<PreferencesParameter> getPreferencesParameters() {
        return preferencesParameters;
    }

    public void setPreferencesParameters(List<PreferencesParameter> preferencesParameters) {
        this.preferencesParameters = preferencesParameters;
    }

    @Override
    public String toString() {
        String result = "";
        boolean isFirst = true;
        for (PreferencesParameter p : preferencesParameters) {
            if (isFirst) {
                result = result.concat(p.toString());
                isFirst = false;
            } else {
                result = result.concat("\n" + p.toString());
            }
        }

        return result;
    }
}
