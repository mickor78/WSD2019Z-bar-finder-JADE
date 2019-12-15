package pl.edu.pw.eiti.wsd.bar_finder.commons.mappers;

import java.util.List;

import pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.*;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.*;

public final class InputToModelMapper {

    public static BarBeer Map(BarBeerData input) {
        if (input == null)
            return null;

        String name = input.getName();
        String breweryName = input.getBreweryName();
        BeerMap.BeerStyle beerStyle = BeerMap.BeerStyle.UNKNOWN;
        double price = input.getPrice();
        double quantity = input.getQuantity();

        if (name == null || name.isEmpty())
            return null;

        try {
            if (input.getStyle() != null)
                beerStyle = BeerMap.BeerStyle.valueOf(input.getStyle());
        } catch (IllegalArgumentException ignored) {
            // TODO: Logging.
        }

        return new BarBeer(name, breweryName, beerStyle, price, quantity);
    }

    public static Bar Map(BarData input) {
        if (input == null)
            return null;

        String name = input.getName();
        String localization = input.getLocalization();
        Integer seatsNumber = input.getSeatsNumber();
        boolean isLoudnessLevelController = input.isLoudnessController();
        boolean isSeatsController = input.isSeatsController();

        if (name == null || name.isEmpty() || localization == null || localization.isEmpty())
            return null;

        Bar bar = new Bar(name, localization, seatsNumber, isLoudnessLevelController, isSeatsController);

        List<BarBeerData> inputBeers = input.getBeers();
        if (inputBeers != null && !inputBeers.isEmpty()) {
            List<BarBeer> beers = bar.getBeers();
            inputBeers.forEach(b -> {
                BarBeer beer = Map(b);
                if (beer != null)
                    beers.add(beer);
            });
        }

        return bar;
    }

    public static PreferencesParameter Map(PreferencesParameterData input) {
        if (input == null)
            return null;

        String name = input.getName();
        String value = input.getValue().toString();
        double importance = input.getImportance();

        if (!ParametersNames.ALL.contains(name) || value == null)
            return null;

        return new PreferencesParameter(name, value, importance);
    }

    public static Preferences Map(PreferencesData input) {
        if (input == null)
            return null;

        Preferences preferences = new Preferences();

        List<PreferencesParameterData> inputPreferencesParameters = input.getPreferences();
        if (inputPreferencesParameters != null && !inputPreferencesParameters.isEmpty()) {
            inputPreferencesParameters.forEach(p -> {
                PreferencesParameter preferencesParameter = Map(p);
                if (preferencesParameter != null) {
                    preferences.getPreferencesParameters().add(preferencesParameter);
                }
            });
        }

        return preferences;
    }
}
