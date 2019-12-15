package pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures;

public class RegionData {

    public final static String REGIONS_KEY = "regions";
    public final static String REGION_NAME_KEY = "name";
    public final static String REGION_LOCALIZATION_KEY = "localization";

    private String name;
    private String localization;

    public RegionData(String name, String localization){
        setName(name);
        setLocalization(localization);
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
}
