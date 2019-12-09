package pl.edu.pw.eiti.wsd.bar_finder.commons.score;

import java.util.List;

import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Bar;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.BarBeer;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesParameter;

import static pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.ParametersNames.*;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.CoordinationUtils.getDistance;

public final class ParametersScore {

    // TODO
    public double score(PreferencesParameter preferencesParameter, Object barValue) {
        if (preferencesParameter == null)
            throw new NullPointerException("preferencesParameter");
        if (barValue == null)
            throw new NullPointerException("barValue");

        String paramName = preferencesParameter.getName();
        if (paramName == null || paramName.isEmpty() || !ALL.contains(paramName))
            throw new IllegalArgumentException("preferencesParameter");

        List<BarBeer> beers = null;
        double result = 0.0;

        switch (paramName) {
            case LOCALIZATION_PARAM_NAME:
                if (barValue instanceof String) {
                    // TODO: XY vs geocoding

                }
                break;
            case BEER_PARAM_NAME:
                beers = getBarBeers(barValue);
                if (beers != null) {

                }
                break;
            case BEER_STYLE_PARAM_NAME:
                beers = getBarBeers(barValue);
                if (beers != null) {

                }
                break;
            case BEER_PRICE_PARAM_NAME:
                beers = getBarBeers(barValue);
                if (beers != null) {

                }
                break;
            case BAR_LOUDNESS_LEVEL_PARAM_NAME:
                if (barValue instanceof Bar.LoudnessLevel) {

                }
                break;
            case BAR_FREE_SEATS_PARAM_NAME:
                if (barValue instanceof Double) {

                }
                break;
        }

        return result;
    }

    // TODO
    private double getLocalizationScore(String customerLocalization, String barLocalization) {
        if (customerLocalization == null)
            throw new NullPointerException("customerLocalization");
        if (customerLocalization.isEmpty())
            throw new IllegalArgumentException("customerLocalization");
        if (barLocalization == null)
            throw new NullPointerException("barLocalization");
        if (barLocalization.isEmpty())
            throw new IllegalArgumentException("barLocalization");

        double distance = getDistance(customerLocalization, barLocalization);

        return 0.0;
    }

    private List<BarBeer> getBarBeers(Object barValue)
    {
        if (barValue instanceof List) {
            List list = (List)barValue;
            if (!list.isEmpty() && list.get(0) instanceof BarBeer) {
                return (List<BarBeer>)barValue;
            }
        }

        return null;
    }
}
