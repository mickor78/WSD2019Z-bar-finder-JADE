package pl.edu.pw.eiti.wsd.bar_finder.utilities;

import java.util.LinkedList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.BarBeerData;
import pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.BarData;
import pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.PreferencesData;
import pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.PreferencesParameterData;

import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.BarBeerData.*;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.BarData.*;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.PreferencesData.CUSTOMER_KEY;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.PreferencesData.PREFERENCES_KEY;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.PreferencesParameterData.*;

public final class JsonUtils {

    public static List<BarData> GetBars(JSONArray data)
    {
        List<BarData> result = new LinkedList<>();

        if (data != null && !data.isEmpty())
        {
            data.forEach(obj -> {
                BarData bar = GetBar((JSONObject)obj);
                if (bar != null)
                    result.add(bar);
            });
        }

        return result;
    }

    public static BarData GetBar(JSONObject data)
    {
        if (data == null || data.isEmpty())
            return null;

        String name = (String)data.get(BAR_NAME_KEY);
        String localization = (String)data.get(BAR_LOCALIZATION_KEY);
        List<BarBeerData> beers = GetBeers((JSONArray)data.get(BAR_BEERS_KEY));
        boolean isLoudnessController = (Boolean)data.get(BAR_IS_LOUDNESS_CONTROLLER_KEY);
        boolean isSeatsController = (Boolean)data.get(BAR_IS_SEATS_CONTROLLER_KEY);

        if (name != null && !name.isEmpty())
            return new BarData(name, localization, beers, isLoudnessController, isSeatsController);

        return null;
    }

    public static List<BarBeerData> GetBeers(JSONArray data)
    {
        List<BarBeerData> result = new LinkedList<>();

        if (data != null && !data.isEmpty())
        {
            data.forEach(obj -> {
                BarBeerData beer = GetBeer((JSONObject)obj);
                if (beer != null)
                    result.add(beer);
            });
        }

        return result;
    }

    public static BarBeerData GetBeer(JSONObject data)
    {
        if (data == null || data.isEmpty())
            return null;

        String name = (String)data.get(BEER_NAME_KEY);
        String breweryName = (String)data.get(BEER_BREWERY_NAME_KEY);
        String style = (String)data.get(BEER_STYLE_KEY);
        double price = (Double)data.get(BEER_PRICE_KEY);
        double quantity = (Double)data.get(BEER_QUANTITY_KEY);

        if (name != null && !name.isEmpty())
            return new BarBeerData(name, breweryName, style, price, quantity);

        return null;
    }

    public static List<PreferencesData> GetCustomersPreferences(JSONArray data)
    {
        List<PreferencesData> result = new LinkedList<>();

        if (data != null && !data.isEmpty())
        {
            data.forEach(obj -> {
                PreferencesData preferences = GetPreferences((JSONObject)obj);
                if (preferences != null)
                    result.add(preferences);
            });
        }

        return result;
    }

    public static PreferencesData GetPreferences(JSONObject data)
    {
        if (data == null || data.isEmpty())
            return null;

        String customer = (String)data.get(CUSTOMER_KEY);
        JSONArray customerPreferencesJSONArray = (JSONArray)data.get(PREFERENCES_KEY);

        if (customer == null || customer.isEmpty() || customerPreferencesJSONArray == null || customerPreferencesJSONArray.isEmpty())
            return null;

        PreferencesData result = new PreferencesData(customer);

        customerPreferencesJSONArray.forEach(obj -> {
            PreferencesParameterData preferencesParameter = GetPreferencesParameter((JSONObject)obj);
            if (preferencesParameter != null)
                result.getPreferences().add(preferencesParameter);
        });

        if (result.getPreferences().isEmpty())
            return null;

        return result;
    }

    public static PreferencesParameterData GetPreferencesParameter(JSONObject data)
    {
        if (data == null || data.isEmpty())
            return null;

        String name = (String)data.get(PREF_PARAM_NAME_KEY);
        Object val = data.get(PREF_PARAM_VAL_KEY);
        double importance = (Double)data.get(PREF_PARAM_IMPORTANCE_KEY);

        if (name != null && !name.isEmpty())
            return new PreferencesParameterData(name, val, importance);

        return null;
    }
}
