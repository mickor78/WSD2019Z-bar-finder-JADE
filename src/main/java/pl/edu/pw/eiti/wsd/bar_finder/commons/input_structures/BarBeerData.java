package pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures;

public class BarBeerData {

    // https://www.wiki.piwo.org/Zestawienie_styl%C3%B3w_piw_(tabela)
    /// TODO: Move to model.
//    public enum BeerStyle
//    {
//        LIGHT_LAGER,
//        PILSNER,
//        EUROPEAN_AMBER_LAGER,
//        DARK_LAGER,
//        BOCK,
//        LIGHT_HYBRID_BEER,
//        AMBER_HYBRID_BEER,
//        ENGLISH_PALE_ALE,
//        SCOTTISH_AND_IRISH_ALE,
//        AMERICAN_ALE,
//        ENGLISH_BROWN_ALE,
//        PORTER,
//        STOUT,
//        INDIA_PALE_ALE,
//        GERMAN_WHEAT_AND_RYE_BEER,
//        BELGIAN_AND_FRENCH_ALE,
//        SOUR_ALE,
//        BELGIAN_STRONG_ALE,
//        STRONG_ALE,
//        FRUIT_BEER,
//        UNKNOWN
//    }

    public final static String BEER_NAME_KEY = "name";
    public final static String BEER_STYLE_KEY = "style";
    public final static String BEER_PRICE_KEY = "price";
    public final static String BEER_QUANTITY_KEY = "quantity";

    private String name;
    private String style;
    private double price;
    private double quantity;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public BarBeerData()
    {}

    public BarBeerData(String name, String style, double price, double quantity)
    {
        setName(name);
        setStyle(style);
        setPrice(price);
        setQuantity(quantity);
    }
}