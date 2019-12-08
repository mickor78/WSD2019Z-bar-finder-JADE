package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;

import java.util.Arrays;
import java.util.List;

public class ParametersNames {
    public static final String LOCALIZATION_PARAM_NAME = "localization";
    public static final String BEER_PARAM_NAME =  "beer";
    public static final String BEER_STYLE_PARAM_NAME = "beer_style";
    public static final String BEER_PRICE_PARAM_NAME = "beer_price";
    public static final String BAR_LOUDNESS_LEVEL_PARAM_NAME = "bar_loudness_level";
    public static final String BAR_FREE_SEATS_PARAM_NAME = "bar_free_seats";
    public static final List<String> ALL =  Arrays.asList(LOCALIZATION_PARAM_NAME, BEER_PARAM_NAME,
            BEER_STYLE_PARAM_NAME, BEER_PRICE_PARAM_NAME, BAR_LOUDNESS_LEVEL_PARAM_NAME, BAR_FREE_SEATS_PARAM_NAME);
}
