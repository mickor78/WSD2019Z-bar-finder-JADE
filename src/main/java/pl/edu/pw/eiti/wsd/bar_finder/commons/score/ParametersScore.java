package pl.edu.pw.eiti.wsd.bar_finder.commons.score;

import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.ParametersNames.*;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.CoordinationUtils.getDistance;

public final class ParametersScore {

    public double score(PreferencesDictionary preferencesParams, Bar offer) {
        double score = 0.0;
        BeerMap beerMap = new BeerMap();
        PreferencesParameter beerParam = preferencesParams.get(BEER_PARAM_NAME);
        boolean isBeer = false;
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
                    double actualQuantity = 0.0;
                    beerList = offer.getBeers();
                    for (BarBeer beer: beerList) {
                        if(beer.getName().equalsIgnoreCase(barValue)) {
                            actualQuantity = beer.getQuantity();
                            if (actualQuantity >= desiredQuantity) {
                                score += 5 * preferencesParameter.getImportance();
                                isBeer = true;
                                break;
                            }
                            else if (actualQuantity >= 0.3 * desiredQuantity) {
                                score += 5 * (10 / (7 * desiredQuantity) * actualQuantity - 3 / 7) * preferencesParameter.getImportance();
                                isBeer = true;
                                break;
                            }
                        }
                    }
                    break;
                case BEER_STYLE_PARAM_NAME:
                    if(!isBeer){
                        Map<BeerMap.BeerStyle, BeerMap.BeerGroup>  beerStyles = beerMap.getMap();
                        double styleScore = 0.0;
                        double similarScore = 0.0;
                        List<BeerMap.BeerStyle> similarBeerList;
                        beerList = offer.getBeers();
                        for (BarBeer beer : beerList) {
                            if (beer.getStyle().toString().equalsIgnoreCase(barValue)) {
                                if (beer.getQuantity() > desiredQuantity) {
                                    styleScore += 1;
                                }
                                else if (beer.getQuantity() >= 0.3 * desiredQuantity) {
                                    styleScore += (10 / (7 * desiredQuantity) * beer.getQuantity() - 3 / 7);
                                }
                            }
                        }
                        similarBeerList = beerMap.getSimilar(BeerMap.BeerStyle.valueOf(barValue));
                        for(BeerMap.BeerStyle similarBeer : similarBeerList){
                            for(BarBeer beer : beerList){
                                if(beer.getStyle() == similarBeer && beer.getStyle() != BeerMap.BeerStyle.valueOf(barValue)){
                                    if (beer.getQuantity() >= desiredQuantity) {
                                        similarScore += 0.3;
                                    }
                                    else if (beer.getQuantity() >= 0.3 * desiredQuantity) {
                                        similarScore += 0.3 * (10 / (7 * desiredQuantity) * beer.getQuantity() - 3 / 7);
                                    }
                                }
                            }
                        }
                        if(styleScore >= 3) styleScore = 3 ;
                        if(similarScore >= 1) similarScore = 1;
                        score += (styleScore + similarScore) * preferencesParameter.getImportance();
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
                    break;
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
}
