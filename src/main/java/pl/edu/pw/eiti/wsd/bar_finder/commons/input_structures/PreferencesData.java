package pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures;

import java.util.LinkedList;
import java.util.List;

public class PreferencesData {

    private List<PreferencesParameterData> preferences;

    public List<PreferencesParameterData> getPreferences() {
        return preferences;
    }
    public void setPreferences(List<PreferencesParameterData> preferences) {
        this.preferences = preferences;
    }

    public PreferencesData()
    {
        preferences = new LinkedList<PreferencesParameterData>();
    }

    public PreferencesData(List<PreferencesParameterData> preferences)
    {
        setPreferences(preferences);
    }
}
