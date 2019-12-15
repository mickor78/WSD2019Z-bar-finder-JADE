package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BeerMap {
    public Map<BeerStyle, BeerGroup> beers;

    public BeerMap(){
        beers = new HashMap<>();
        beers.put(BeerStyle.LITE_AMERICAN_LAGER, BeerGroup.LIGHT_LAGER);
        beers.put(BeerStyle.STANDARD_AMERICAN_LAGER, BeerGroup.LIGHT_LAGER);
        beers.put(BeerStyle.PREMIUM_AMERICAN_LAGER, BeerGroup.LIGHT_LAGER);
        beers.put(BeerStyle.MUNICH_HELLES, BeerGroup.LIGHT_LAGER);
        beers.put(BeerStyle.DORTMUNDER_EXPORT, BeerGroup.LIGHT_LAGER);
        beers.put(BeerStyle.GERMAN_PILSNER, BeerGroup.PILSNER);
        beers.put(BeerStyle.BOHEMIAN_PILSNER, BeerGroup.PILSNER);
        beers.put(BeerStyle.CLASSIC_AMERICAN_PILSNER, BeerGroup.PILSNER);
        beers.put(BeerStyle.VIENNA_LAGER, BeerGroup.EUROPEAN_AMBER_LAGER);
        beers.put(BeerStyle.OKTOBERFEST, BeerGroup.EUROPEAN_AMBER_LAGER);
        beers.put(BeerStyle.DARK_AMERICAN_LAGER, BeerGroup.DARK_LAGER);
        beers.put(BeerStyle.MUNICH_DUNKEL, BeerGroup.DARK_LAGER);
        beers.put(BeerStyle.SCHWARZBIER, BeerGroup.DARK_LAGER);
        beers.put(BeerStyle.HELLES_BOCK, BeerGroup.BOCK);
        beers.put(BeerStyle.TRADITIONAL_BOCK, BeerGroup.BOCK);
        beers.put(BeerStyle.DOPPELBOCK, BeerGroup.BOCK);
        beers.put(BeerStyle.EISBOCK, BeerGroup.BOCK);
        beers.put(BeerStyle.CREAM_ALE, BeerGroup.LIGHT_HYBRID_BEER);
        beers.put(BeerStyle.BLONDE_ALE, BeerGroup.LIGHT_HYBRID_BEER);
        beers.put(BeerStyle.KOLSCH, BeerGroup.LIGHT_HYBRID_BEER);
        beers.put(BeerStyle.AMERICAN_WHEAT, BeerGroup.LIGHT_HYBRID_BEER);
        beers.put(BeerStyle.NORTH_GERMAN_ALTBIER, BeerGroup.AMBER_HYBRID_BEER);
        beers.put(BeerStyle.CALIFORNIA_COMMON_BEER, BeerGroup.AMBER_HYBRID_BEER);
        beers.put(BeerStyle.DUSSELDORF_ALTBIER, BeerGroup.AMBER_HYBRID_BEER);
        beers.put(BeerStyle.STANDARD_BITTER, BeerGroup.ENGLISH_PALE_ALE);
        beers.put(BeerStyle.SPECIAL_BITTER, BeerGroup.ENGLISH_PALE_ALE);
        beers.put(BeerStyle.EXTRA_SPECIAL_BITTER, BeerGroup.ENGLISH_PALE_ALE);
        beers.put(BeerStyle.SCOTTISH_LIGHT, BeerGroup.SCOTTISH_AND_IRISH_ALE);
        beers.put(BeerStyle.SCOTTISH_HEAVY, BeerGroup.SCOTTISH_AND_IRISH_ALE);
        beers.put(BeerStyle.SCOTTISH_EXPORT, BeerGroup.SCOTTISH_AND_IRISH_ALE);
        beers.put(BeerStyle.IRISH_RED_ALE, BeerGroup.SCOTTISH_AND_IRISH_ALE);
        beers.put(BeerStyle.STRONG_SCOTCH_ALE, BeerGroup.SCOTTISH_AND_IRISH_ALE);
        beers.put(BeerStyle.AMERICAN_PALE_ALE, BeerGroup.AMERICAN_ALE);
        beers.put(BeerStyle.AMERICAN_AMBER_ALE, BeerGroup.AMERICAN_ALE);
        beers.put(BeerStyle.AMERICAN_BROWN_ALE, BeerGroup.AMERICAN_ALE);
        beers.put(BeerStyle.MILD, BeerGroup.ENGLISH_BROWN_ALE);
        beers.put(BeerStyle.SOUTHERN_ENGLISH_BROWN, BeerGroup.ENGLISH_BROWN_ALE);
        beers.put(BeerStyle.NORTHERN_ENGLISH_BROWN, BeerGroup.ENGLISH_BROWN_ALE);
        beers.put(BeerStyle.BROWN_PORTER, BeerGroup.PORTER);
        beers.put(BeerStyle.ROBUST_PORTER, BeerGroup.PORTER);
        beers.put(BeerStyle.BALTIC_PORTER, BeerGroup.PORTER);
        beers.put(BeerStyle.DRY_STOUT, BeerGroup.STOUT);
        beers.put(BeerStyle.SWEET_STOUT, BeerGroup.STOUT);
        beers.put(BeerStyle.OATMEAL_STOUT, BeerGroup.STOUT);
        beers.put(BeerStyle.FOREIGN_EXTRA_STOUT, BeerGroup.STOUT);
        beers.put(BeerStyle.AMERICAN_STOUT, BeerGroup.STOUT);
        beers.put(BeerStyle.RUSSIAN_IMPERIAL_STOUT, BeerGroup.STOUT);
        beers.put(BeerStyle.ENGLISH_IPA, BeerGroup.INDIA_PALE_ALE);
        beers.put(BeerStyle.AMERICAN_IPA, BeerGroup.INDIA_PALE_ALE);
        beers.put(BeerStyle.IMPERIAL_IPA, BeerGroup.INDIA_PALE_ALE);
        beers.put(BeerStyle.WEIZEN, BeerGroup.GERMAN_WHEAT_AND_RYE_BEER);
        beers.put(BeerStyle.DUNKELWEIZEN, BeerGroup.GERMAN_WHEAT_AND_RYE_BEER);
        beers.put(BeerStyle.WEIZENBOCK, BeerGroup.GERMAN_WHEAT_AND_RYE_BEER);
        beers.put(BeerStyle.ROGGENBIER, BeerGroup.GERMAN_WHEAT_AND_RYE_BEER);
        beers.put(BeerStyle.WITBIER, BeerGroup.BELGIAN_AND_FRENCH_ALE);
        beers.put(BeerStyle.BELGIAN_PALE_ALE, BeerGroup.BELGIAN_AND_FRENCH_ALE);
        beers.put(BeerStyle.SAISON, BeerGroup.BELGIAN_AND_FRENCH_ALE);
        beers.put(BeerStyle.BIERE_DE_GARDE, BeerGroup.BELGIAN_AND_FRENCH_ALE);
        beers.put(BeerStyle.BERLINER_WEISSE, BeerGroup.SOUR_ALE);
        beers.put(BeerStyle.FLANDERS_RED_ALE, BeerGroup.SOUR_ALE);
        beers.put(BeerStyle.FLANDERS_BROWN_ALE, BeerGroup.SOUR_ALE);
        beers.put(BeerStyle.STRAIGHT_LAMBIC, BeerGroup.SOUR_ALE);
        beers.put(BeerStyle.GUEUZE, BeerGroup.SOUR_ALE);
        beers.put(BeerStyle.FRUIT_LAMBIC, BeerGroup.SOUR_ALE);
        beers.put(BeerStyle.BELGIAN_BLOND_ALE, BeerGroup.BELGIAN_STRONG_ALE);
        beers.put(BeerStyle.BELGIAN_DUBBEL, BeerGroup.BELGIAN_STRONG_ALE);
        beers.put(BeerStyle.BELGIAN_TRIPEL, BeerGroup.BELGIAN_STRONG_ALE);
        beers.put(BeerStyle.BELGIAN_GOLDEN_STRONG_ALE, BeerGroup.BELGIAN_STRONG_ALE);
        beers.put(BeerStyle.BELGIAN_DARK_STRONG_ALE, BeerGroup.BELGIAN_STRONG_ALE);
        beers.put(BeerStyle.OLD_ALE, BeerGroup.STRONG_ALE);
        beers.put(BeerStyle.ENGLISH_BARLEYWINE, BeerGroup.STRONG_ALE);
        beers.put(BeerStyle.AMERICAN_BARLEYWINE, BeerGroup.STRONG_ALE);
    }

    public Map<BeerStyle, BeerGroup> getMap () {
        return beers;
    }
    BeerGroup getGroup (BeerStyle beerStyle) {
        BeerGroup beerGroup = beers.get(beerStyle);
        return beerGroup;
    }

    List<BeerStyle> getFromGroup (BeerGroup beerGroup){
        List<BeerStyle> similarBeer = new LinkedList<BeerStyle>();
        for(Map.Entry<BeerStyle, BeerGroup> beer : beers.entrySet() ){
            if(beer.getValue() == beerGroup)
                similarBeer.add(beer.getKey());
        }
        return similarBeer;
    }

    public List<BeerStyle> getSimilar (BeerStyle beerStyle){
        List<BeerStyle> similarBeer = new LinkedList<BeerStyle>();
        similarBeer = this.getFromGroup(getGroup (beerStyle));
        return similarBeer;
    }

    public enum BeerStyle {
        UNKNOWN,

        LITE_AMERICAN_LAGER,
        STANDARD_AMERICAN_LAGER,
        PREMIUM_AMERICAN_LAGER,
        MUNICH_HELLES,
        DORTMUNDER_EXPORT,

        GERMAN_PILSNER,
        BOHEMIAN_PILSNER,
        CLASSIC_AMERICAN_PILSNER,

        VIENNA_LAGER,
        OKTOBERFEST,

        DARK_AMERICAN_LAGER,
        MUNICH_DUNKEL,
        SCHWARZBIER,

        HELLES_BOCK,
        TRADITIONAL_BOCK,
        DOPPELBOCK,
        EISBOCK,

        CREAM_ALE,
        BLONDE_ALE,
        KOLSCH,
        AMERICAN_WHEAT,

        NORTH_GERMAN_ALTBIER,
        CALIFORNIA_COMMON_BEER,
        DUSSELDORF_ALTBIER,

        STANDARD_BITTER,
        SPECIAL_BITTER,
        EXTRA_SPECIAL_BITTER,

        SCOTTISH_LIGHT,
        SCOTTISH_HEAVY,
        SCOTTISH_EXPORT,
        IRISH_RED_ALE,
        STRONG_SCOTCH_ALE,

        AMERICAN_PALE_ALE,
        AMERICAN_AMBER_ALE,
        AMERICAN_BROWN_ALE,

        MILD,
        SOUTHERN_ENGLISH_BROWN,
        NORTHERN_ENGLISH_BROWN,

        BROWN_PORTER,
        ROBUST_PORTER,
        BALTIC_PORTER,

        DRY_STOUT,
        SWEET_STOUT,
        OATMEAL_STOUT,
        FOREIGN_EXTRA_STOUT,
        AMERICAN_STOUT,
        RUSSIAN_IMPERIAL_STOUT,

        ENGLISH_IPA,
        AMERICAN_IPA,
        IMPERIAL_IPA,

        WEIZEN,
        DUNKELWEIZEN,
        WEIZENBOCK,
        ROGGENBIER,

        WITBIER,
        BELGIAN_PALE_ALE,
        SAISON,
        BIERE_DE_GARDE,

        BERLINER_WEISSE,
        FLANDERS_RED_ALE,
        FLANDERS_BROWN_ALE,
        STRAIGHT_LAMBIC,
        GUEUZE,
        FRUIT_LAMBIC,

        BELGIAN_BLOND_ALE,
        BELGIAN_DUBBEL,
        BELGIAN_TRIPEL,
        BELGIAN_GOLDEN_STRONG_ALE,
        BELGIAN_DARK_STRONG_ALE,

        OLD_ALE,
        ENGLISH_BARLEYWINE,
        AMERICAN_BARLEYWINE
    }

    public enum BeerGroup {
        UNKNOWN,
        LIGHT_LAGER,
        PILSNER,
        EUROPEAN_AMBER_LAGER,
        DARK_LAGER,
        BOCK,
        LIGHT_HYBRID_BEER,
        AMBER_HYBRID_BEER,
        ENGLISH_PALE_ALE,
        SCOTTISH_AND_IRISH_ALE,
        AMERICAN_ALE,

        ENGLISH_BROWN_ALE,
        PORTER,
        STOUT,
        INDIA_PALE_ALE,
        GERMAN_WHEAT_AND_RYE_BEER,
        BELGIAN_AND_FRENCH_ALE,
        SOUR_ALE,
        BELGIAN_STRONG_ALE,
        STRONG_ALE
    }
}
