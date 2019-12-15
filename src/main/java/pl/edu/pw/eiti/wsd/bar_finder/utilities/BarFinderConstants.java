package pl.edu.pw.eiti.wsd.bar_finder.utilities;

public class BarFinderConstants {

    public static final String BAR_AGENT = "barAgent";
    public static final String CUSTOMER_AGENT = "customerAgent";
    public static final String LOUDNESS_CONTROLLER_AGENT = "loudnessControllerAgent";
    public static final String RESOURCES_CONTROLLER_AGENT = "resourcesControllerAgent";
    public static final String SEATS_CONTROLLER_AGENT = "seatsControllerAgent";

    public static final String BAR_AGENT_ROLE_BOM = "BarOfferManager";
    public static final String BAR_AGENT_ROLE_BOH = "BestOfferHolder";

    public static final String BASE_PACKAGE_PATH = "pl.edu.pw.eiti.wsd.bar_finder";

    public static final String BAR_AGENT_CLASS_PATH = BASE_PACKAGE_PATH + ".bar_agent.BarAgent";
    public static final String CUSTOMER_AGENT_CLASS_PATH = BASE_PACKAGE_PATH + ".customer_agent.CustomerAgent";
    public static final String LOUDNESS_CONTROLLER_AGENT_CLASS_PATH = BASE_PACKAGE_PATH + ".bar_controller_agent.loudness_controller_agent.LoudnessControllerAgent";
    public static final String RESOURCES_CONTROLLER_AGENT_CLASS_PATH = BASE_PACKAGE_PATH + ".bar_controller_agent.resources_controller_agent.ResourcesControllerAgent";
    public static final String SEATS_CONTROLLER_AGENT_CLASS_PATH = BASE_PACKAGE_PATH + ".bar_controller_agent.seats_controller_agent.SeatsControllerAgent";

    public static final String LOUDNESS_CONTROLLER_AGENT_NAME = "loudness_controller_agent";
    public static final String SEATS_CONTROLLER_AGENT_NAME = "seats_controller_agent";
    public static final String RESOURCES_CONTROLLER_AGENT_NAME = "resources_controller_agent";
}
