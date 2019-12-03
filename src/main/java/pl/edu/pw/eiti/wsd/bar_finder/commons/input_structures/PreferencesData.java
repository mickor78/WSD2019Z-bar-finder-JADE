package pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures;

import java.util.LinkedList;
import java.util.List;

public class PreferencesData {

    public final static String CUSTOMERS_PREFERENCES_KEY = "customers_preferences";
    public final static String PREFERENCES_KEY = "preferences";
    public final static String CUSTOMER_KEY = "customer";

    private List<PreferencesParameterData> preferences;
    private String customer;

    public List<PreferencesParameterData> getPreferences() {
        return preferences;
    }
    public void setPreferences(List<PreferencesParameterData> preferences) {
        this.preferences = preferences;
    }

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public PreferencesData(String customer)
    {
        setCustomer(customer);
        preferences = new LinkedList<>();
    }

    public PreferencesData(String customer, List<PreferencesParameterData> preferences)
    {
        setCustomer(customer);
        setPreferences(preferences);
    }
}
