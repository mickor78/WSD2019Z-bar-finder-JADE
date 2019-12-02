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

public final class JsonUtils {

    public static List<BarData> GetBars(JSONArray data)
    {
        List<BarData> result = new LinkedList<BarData>();

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

        return new BarData(name, localization, beers, isLoudnessController, isSeatsController);
    }

    public static List<BarBeerData> GetBeers(JSONArray data)
    {
        List<BarBeerData> result = new LinkedList<BarBeerData>();

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
        String style = (String)data.get(BEER_STYLE_KEY);
        double price = (Double)data.get(BEER_PRICE_KEY);
        double quantity = (Double)data.get(BEER_QUANTITY_KEY);

        return new BarBeerData(name, style, price, quantity);
    }

    public static PreferencesData GetPreferences(JSONArray data)
    {
        return null;
    }

    public static PreferencesParameterData GetPreferecesParameter(JSONObject data)
    {
        return null;
    }
}
