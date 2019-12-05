package pl.edu.pw.eiti.wsd.bar_finder.utilities;

public final class BarFinderAgentNameUtils {

    public static String GetBarControllerName(String barName, String controllerAgentName)
    {
        if (barName == null || barName.isEmpty() || controllerAgentName == null || controllerAgentName.isEmpty())
            return null;

        return String.format("bar_%s_%s", barName, controllerAgentName);
    }
}
