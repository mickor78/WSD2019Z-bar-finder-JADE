package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bar implements Serializable {

    private String name;
    private String localization;
    private List<BarBeer> beers;
    private boolean isLoudnessController = true;
    private boolean isSeatsController = true;
    private Integer seatsNumber;
    private LoudnessLevel loudnessLevel = LoudnessLevel.UNKNOWN;
    private Integer freeSeats;
    public Bar() {
        this.beers = new ArrayList<>();
    }

    public Bar(String name, String localization, Integer seatsNumber, boolean isLoudnessController, boolean isSeatsController) {
        setName(name);
        setLocalization(localization);
        this.beers = new ArrayList<>();
        setSeatsNumber(seatsNumber);
        setIsLoudnessController(isLoudnessController);
        setIsSeatsController(isSeatsController);
    }

    public Bar(String name, String localization, List<BarBeer> beers, Integer seatsNumber,
               boolean isLoudnessController, boolean isSeatsController) {
        setName(name);
        setLocalization(localization);
        setBeers(beers);
        setSeatsNumber(seatsNumber);
        setIsLoudnessController(isLoudnessController);
        setIsSeatsController(isSeatsController);
    }

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

    public List<BarBeer> getBeers() {
        return beers;
    }

    public void setBeers(List<BarBeer> beers) {
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

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public LoudnessLevel getLoudnessLevel() {
        return loudnessLevel;
    }

    public void setLoudnessLevel(LoudnessLevel loudnessLevel) {
        this.loudnessLevel = loudnessLevel;
    }

    public Integer getFreeSeats() {
        return freeSeats;
    }

    public void setFreeSeats(Integer freeSeats) {
        this.freeSeats = freeSeats;
    }

    public enum LoudnessLevel {
        UNKNOWN,
        QUIET,
        MEDIUM,
        NOISE,
    }
}

