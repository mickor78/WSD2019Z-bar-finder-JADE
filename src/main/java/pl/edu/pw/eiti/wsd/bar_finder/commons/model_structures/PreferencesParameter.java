package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;

import jade.content.Concept;

public class PreferencesParameter implements Concept {

    private String name;
    // Due to ontology, this field must be string
    private String value;
    private double importance;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }

    public double getImportance() {
        return importance;
    }
    public void setImportance(double importance) {
        this.importance = importance < 0.0 ?
            0.0 : importance > 1.0 ? 1.0 : importance;
    }

    public PreferencesParameter()
    {}

    public PreferencesParameter(String name, String value, double importance)
    {
        setName(name);
        setValue(value);
        setImportance(importance);
    }

    @Override
    public String toString()
    {
        return String.format("Preferences parameter: name - %s, value - %s, importance - %.2f", name, value, importance);
    }
}
