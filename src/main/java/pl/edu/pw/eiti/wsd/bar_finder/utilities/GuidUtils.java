package pl.edu.pw.eiti.wsd.bar_finder.utilities;

import java.util.UUID;

public final class GuidUtils {
    public static String GetGuid() {
        return UUID.randomUUID().toString();
    }
}
