package pl.edu.pw.eiti.wsd.bar_finder.utilities;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public final class CoordinationUtils {

    public static double getDistance(String coordsText1, String coordsText2) {
        if (coordsText1 == null)
            throw new NullPointerException("coordsText1");
        if (coordsText1.isEmpty())
            throw new IllegalArgumentException("coordsText1");
        if (coordsText2 == null)
            throw new NullPointerException("coordsText2");
        if (coordsText2.isEmpty())
            throw new IllegalArgumentException("coordsText2");

        Point coords1 = getCoords(coordsText1);
        Point coords2 = getCoords(coordsText2);

        return Math.hypot(coords1.getX() - coords2.getX(), coords1.getY() - coords2.getY());
    }

    private static Point getCoords(String coords) {
        if (coords == null)
            throw new NullPointerException("coords");
        if (coords.isEmpty())
            throw new IllegalArgumentException("coords");

        String[] coordsArray = coords.split(";");
        if (coordsArray.length != 2)
            throw new IllegalArgumentException("coords");

        NumberFormat format = NumberFormat.getInstance(Locale.ENGLISH);
        double x = 0, y = 0;
        try {
            x = format.parse(coordsArray[0]).doubleValue();
            y = format.parse(coordsArray[1]).doubleValue();
        }
        catch (ParseException e) {
            // TODO:
            e.printStackTrace();
        }

        return new Point(x, y);
    }
}
