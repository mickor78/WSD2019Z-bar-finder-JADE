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

            double desiredQuantity = 10.0;
            List<BarBeer> beerList;

            switch (paramName) {
                case LOCALIZATION_PARAM_NAME:
                    double distance = getDistance(offer.getLocalization(), barValue);
                    if(distance < 1000)
                        score += (1 - 0.0005 * distance) * preferencesParameter.getImportance();
                    else if(distance < 5000)
                        score += 0.5 * preferencesParameter.getImportance();
                    break;

                case BEER_PARAM_NAME:
                    double styleQuantity = 0, actualQuantity = 0;
                    beerList = offer.getBeers();
                    for (BarBeer beer: beerList) {
                        if(beer.getName().equalsIgnoreCase(barValue)) {
                            actualQuantity = beer.getQuantity();
                            if (actualQuantity > desiredQuantity) {
                                score += preferencesParameter.getImportance();
                                break;
                            }
                        }
                    }
                    break;
                case BEER_STYLE_PARAM_NAME:
                    styleQuantity = 0;
                    beerList = offer.getBeers();
                    for (BarBeer beer : beerList) {
                        if (beer.getStyle().toString().equalsIgnoreCase(barValue)) {
                            styleQuantity += beer.getQuantity();
                        }
                    }
                    if (styleQuantity > desiredQuantity) {
                        score += preferencesParameter.getImportance();
                    }
                    break;
                case BEER_PRICE_PARAM_NAME:
                    long numerOfCheapEnoughBeers = offer.getBeers().stream()
                            .map(BarBeer::getPrice)
                            .filter(price -> price < Double.valueOf(barValue))
                            .count();
                    if (numerOfCheapEnoughBeers == 0)
                        break;
                    else if (numerOfCheapEnoughBeers == 1) {
                        score += 0.5 * preferencesParameter.getImportance();
                        break;
                    }
                    else if (numerOfCheapEnoughBeers <= 3) {
                        score += 0.8 * preferencesParameter.getImportance();
                        break;
                    }
                    else
                        score += preferencesParameter.getImportance();
                    break;
                case BAR_LOUDNESS_LEVEL_PARAM_NAME:
                    Bar.LoudnessLevel actualLoudness = offer.getLoudnessLevel();
                    String desiredLoudness = barValue;
                    switch(desiredLoudness) {
                        case "QUIET":
                            switch (actualLoudness) {
                                case QUIET:
                                    score += preferencesParameter.getImportance();
                                    break;
                                case MEDIUM:
                                    score += 0.5 * preferencesParameter.getImportance();
                                    break;
                                default:
                                    break;
                            }
                        case "MEDIUM":
                            switch (actualLoudness) {
                                case MEDIUM:
                                    score += preferencesParameter.getImportance();
                                    break;
                                default:
                                    score += 0.5 * preferencesParameter.getImportance();
                                    break;
                            }
                        case "NOISE":
                            switch (actualLoudness) {
                                case NOISE:
                                    score += preferencesParameter.getImportance();
                                    break;
                                case MEDIUM:
                                    score += 0.5 * preferencesParameter.getImportance();
                                    break;
                                default:
                                    break;
                            }
                    }
                case BAR_FREE_SEATS_PARAM_NAME:
                    if(offer.getFreeSeats() != null) {
                        int freeSeats = offer.getFreeSeats();
                        int desiredSeats = Integer.valueOf(barValue);
                        if (freeSeats >= 2 * desiredSeats)
                            score += preferencesParameter.getImportance();
                        else if (freeSeats >= desiredSeats)
                            score += 0.5 * preferencesParameter.getImportance();
                    }
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
