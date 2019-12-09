package pl.edu.pw.eiti.wsd.bar_finder.commons.score;

import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.*;

import java.util.List;
import java.util.Objects;

import static pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.ParametersNames.*;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.CoordinationUtils.getDistance;

public final class ParametersScore {

    // TODO
    public double score(PreferencesDictionary preferencesParams, Bar offer) {
        double score = 0.0;
        for (String paramName : ParametersNames.ALL
        ) {
            PreferencesParameter preferencesParameter = preferencesParams.get(paramName);
            if (Objects.isNull(preferencesParameter)) continue;
            String barValue = preferencesParameter.getValue();

            boolean canWeFacilitate;
            switch (paramName) {
                case LOCALIZATION_PARAM_NAME:

                    break;
                case BEER_PARAM_NAME:
                    canWeFacilitate = offer.getBeers().stream()
                            .map(BarBeer::getName)
                            .anyMatch(name -> name.equalsIgnoreCase(barValue));
                    if (canWeFacilitate)
                        score += preferencesParameter.getImportance();
                    break;
                case BEER_STYLE_PARAM_NAME:
                    canWeFacilitate = offer.getBeers().stream()
                            .map(BarBeer::getStyle)
                            .map(BarBeer.BeerStyle::name)
                            .anyMatch(name -> name.equalsIgnoreCase(barValue));
                    if (canWeFacilitate)
                        score += preferencesParameter.getImportance();
                    break;
                case BEER_PRICE_PARAM_NAME:
                    long numerOfCheapEnoughBeers = offer.getBeers().stream()
                            .map(BarBeer::getPrice)
                            .filter(price -> price < Double.valueOf(barValue))
                            .count();
                    long allBearsInOffer = offer.getBeers().size();
                    score += preferencesParameter.getImportance()
                            * (double) numerOfCheapEnoughBeers / allBearsInOffer;
                    break;
                case BAR_LOUDNESS_LEVEL_PARAM_NAME:
                    canWeFacilitate = barValue.equalsIgnoreCase(offer
                            .getLoudnessLevel().name());
                    if (canWeFacilitate)
                        score += preferencesParameter.getImportance();
                    break;
                case BAR_FREE_SEATS_PARAM_NAME:
                    int freeSeats = offer.getFreeSeats();
                    canWeFacilitate = Integer.valueOf(barValue) < freeSeats;
                    if (canWeFacilitate)
                        score += preferencesParameter.getImportance();
                    break;
            }
        }
        return score;
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
}
