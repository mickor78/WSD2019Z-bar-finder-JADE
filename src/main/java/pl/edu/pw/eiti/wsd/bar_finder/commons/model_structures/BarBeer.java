package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;

import java.io.Serializable;

public class BarBeer implements Serializable {

    private String name;
    private String breweryName;
    private BeerMap.BeerStyle style;
    private double price;
    private double quantity;

    public BarBeer() {
    }

    public BarBeer(String name, String breweryName, BeerMap.BeerStyle style, double price, double quantity) {
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

    public BeerMap.BeerStyle getStyle() {
        return style;
    }

    public void setStyle(BeerMap.BeerStyle style) {
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

    @Override
    public String toString() {
        return String.format("Beer: Name - %s, BreweryName - %s, Style - %s, Price - %.2f, Quantity - %.2f",
                name, breweryName, style.name(), price, quantity);
    }
}
