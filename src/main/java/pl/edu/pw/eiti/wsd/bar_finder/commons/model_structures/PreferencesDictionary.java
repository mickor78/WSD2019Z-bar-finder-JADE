package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;

import java.util.*;

public class PreferencesDictionary extends Dictionary<String, PreferencesParameter> {

    private Dictionary<String, PreferencesParameter> preferencesDict;

    public PreferencesDictionary()
    {
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

    public List<PreferencesParameter> values() {
        return Collections.list(preferencesDict.elements());
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
