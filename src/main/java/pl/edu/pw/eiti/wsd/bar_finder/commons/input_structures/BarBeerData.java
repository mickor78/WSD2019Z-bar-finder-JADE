package pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures;

public class BarBeerData {

    public final static String BEER_NAME_KEY = "name";
    public final static String BEER_BREWERY_NAME_KEY = "brewery";
    public final static String BEER_STYLE_KEY = "style";
    public final static String BEER_PRICE_KEY = "price";
    public final static String BEER_QUANTITY_KEY = "quantity";

    private String name;
    private String breweryName;
    private String style;
    private double price;
    private double quantity;

    public BarBeerData() {
    }

    public BarBeerData(String name, String breweryName, String style, double price, double quantity) {
        setName(name);
        setBreweryName(breweryName);
        setStyle(style);
        setPrice(price);
        setQuantity(quantity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreweryName() {
        return breweryName;
    }

    public void setBreweryName(String breweryName) {
        this.breweryName = breweryName;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}