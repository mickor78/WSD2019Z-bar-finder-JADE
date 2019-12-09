package pl.edu.pw.eiti.wsd.bar_finder.commons.mappers;

import java.util.*;

import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Preferences;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesDictionary;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesParameter;

public final class PreferencesMapper {

    public static PreferencesDictionary Map(Preferences input) {
        if (input == null)
            return null;

        PreferencesDictionary result = new PreferencesDictionary();

        if (!input.getPreferencesParameters().isEmpty()) {
            input.getPreferencesParameters().forEach(p -> {
                String name = p.getName();
                if (name != null && !name.isEmpty())
                    result.put(name, p);
            });
        }

        return result;
    }

    public Preferences Map(PreferencesDictionary input) {
        if (input == null)
            return null;

        Preferences result = new Preferences();
        List<PreferencesParameter> resultParametersList = result.getPreferencesParameters();

        for (Enumeration<PreferencesParameter> e = input.elements(); e.hasMoreElements(); ) {
            PreferencesParameter p = e.nextElement();
            String pName = p.getName();
            if (pName != null && !pName.isEmpty())
                resultParametersList.add(p);
        }

        return result;
    }
}
