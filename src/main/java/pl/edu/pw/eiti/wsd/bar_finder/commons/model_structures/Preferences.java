package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class Preferences extends Dictionary<String, PreferencesParameter> {

    private String customer;
    private Dictionary<String, PreferencesParameter> preferencesDict;

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Preferences(String customer)
    {
        setCustomer(customer);
        preferencesDict = new Hashtable<>();
    }

    public int size() {
        return preferencesDict.size();
    }

    public boolean isEmpty() {
        return preferencesDict.isEmpty();
    }

    public Enumeration<String> keys() {
        return preferencesDict.keys();
    }

    public Enumeration<PreferencesParameter> elements() {
        return preferencesDict.elements();
    }

    public PreferencesParameter get(Object key) {
        return preferencesDict.get(key);
    }

    public PreferencesParameter put(String key, PreferencesParameter value) {
        return preferencesDict.put(key, value);
    }

    public PreferencesParameter remove(Object key) {
        return preferencesDict.remove(key);
    }
}
