package pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures;

import java.util.List;

public class BarData {

    public final static String BAR_NAME_KEY = "name";
    public final static String BAR_LOCALIZATION_KEY = "localization";
    public final static String BAR_BEERS_KEY = "beers";
    public final static String BAR_IS_LOUDNESS_CONTROLLER_KEY = "loudness_controller";
    public final static String BAR_IS_SEATS_CONTROLLER_KEY = "seats_controller";

    private String name;
    private String localization;
    private List<BarBeerData> beers;
    private boolean isLoudnessController = true;
    private boolean isSeatsController = true;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getLocalization() {
        return localization;
    }
    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public List<BarBeerData> getBeers() {
        return beers;
    }
    public void setBeers(List<BarBeerData> beers) {
        this.beers = beers;
    }

    public boolean isLoudnessController() {
        return isLoudnessController;
    }
    public void setIsLoudnessController(boolean loudnessController) {
        isLoudnessController = loudnessController;
    }

    public boolean isSeatsController() {
        return isSeatsController;
    }
    public void setIsSeatsController(boolean seatsController) {
        isSeatsController = seatsController;
    }

    public BarData()
    {}

    public BarData(String name, String localization, List<BarBeerData> beers)
    {
        setName(name);
        setLocalization(localization);
        setBeers(beers);
    }

    public BarData(String name, String localization, List<BarBeerData> beers,
                   boolean isLoudnessController, boolean isSeatsController)
    {
        setName(name);
        setLocalization(localization);
        setBeers(beers);
        setIsLoudnessController(isLoudnessController);
        setIsSeatsController(isSeatsController);
    }
}