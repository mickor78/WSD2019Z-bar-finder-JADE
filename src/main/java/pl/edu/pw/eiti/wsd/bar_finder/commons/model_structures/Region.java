package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;

public class Region {

    private String name;
    private String localization;

    public Region(String name, String localization){
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
