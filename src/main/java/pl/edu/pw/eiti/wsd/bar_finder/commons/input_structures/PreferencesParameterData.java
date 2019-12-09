package pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures;

public class PreferencesParameterData {

    public final static String PREF_PARAM_NAME_KEY = "name";
    public final static String PREF_PARAM_VAL_KEY = "value";
    public final static String PREF_PARAM_IMPORTANCE_KEY = "importance";

    private String name;
    private Object value;
    private double importance;

    public PreferencesParameterData() {
    }

    public PreferencesParameterData(String name, Object value, double importance) {
        setName(name);
        setValue(value);
        setImportance(importance);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public double getImportance() {
        return importance;
    }

    public void setImportance(double importance) {
        this.importance = importance;
    }
}
