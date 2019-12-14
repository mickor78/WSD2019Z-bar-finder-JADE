package pl.edu.pw.eiti.wsd.bar_finder.system;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.BarData;
import pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.PreferencesData;
import pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.RegionData;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Bar;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Preferences;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderAgentNameUtils;

import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.BarData.BARS_KEY;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.RegionData.REGIONS_KEY;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.PreferencesData.CUSTOMERS_PREFERENCES_KEY;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.mappers.InputToModelMapper.Map;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JsonUtils.*;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.*;

public class BarFinderSystem
{
    public static void main(String[] args) throws Exception {
        if (args == null || args.length != 3)
            throw new Exception("Wrong given arguments count!");

        String barsFilePath = args[0];
        String preferencesFilePath = args[1];
        String regionsFilePath = args[2];

        if (barsFilePath == null || barsFilePath.isEmpty())
            throw new Exception("Empty filepath that should contain bars data.");

        if (preferencesFilePath == null || preferencesFilePath.isEmpty())
            throw new Exception("Empty filepath that should contain customers preferences data.");

        if (regionsFilePath == null || regionsFilePath.isEmpty())
            throw new Exception("Empty filepath that should contain regions data.");

        JSONParser jsonParser = new JSONParser();

        try (FileReader barsFileReader = new FileReader(barsFilePath);
             FileReader preferencesFileReader = new FileReader(preferencesFilePath);
             FileReader regionsFileReader = new FileReader(regionsFilePath)) {
            JSONObject barsJSONObj = (JSONObject)jsonParser.parse(barsFileReader);
            JSONObject preferencesJSONObj = (JSONObject)jsonParser.parse(preferencesFileReader);
            JSONObject regionsJSONObj = (JSONObject)jsonParser.parse(regionsFileReader);

            if (barsJSONObj == null)
                throw new Exception("JSON parsing failed for bars data.");
            if (preferencesJSONObj == null)
                throw new Exception("JSON parsing failed for customers preferences data.");
            if (regionsJSONObj == null)
                throw new Exception("JSON parsing failed for regions data.");


            JSONArray barsJSONArray = (JSONArray)barsJSONObj.get(BARS_KEY);
            JSONArray preferencesJSONArray = (JSONArray)preferencesJSONObj.get(CUSTOMERS_PREFERENCES_KEY);
            JSONArray regionsJSONArray = (JSONArray)regionsJSONObj.get(REGIONS_KEY);

            if (barsJSONArray.isEmpty())
                throw new Exception("Provided file doesn't contain any bars data.");
            if (preferencesJSONArray.isEmpty())
                throw new Exception("Provided file doesn't contain any customers preferences data.");
            if (regionsJSONArray.isEmpty())
                throw new Exception("Provided file doesn't contain any regions data.");

            List<BarData> bars = GetBars(barsJSONArray);
            List<PreferencesData> preferences = GetCustomersPreferences(preferencesJSONArray);
            List<RegionData> regions = GetRegions(regionsJSONArray);

            if (bars == null || bars.isEmpty())
                throw new Exception("JSON deserialization failed - there are no bars.");
            if (preferences == null || preferences.isEmpty())
                throw new Exception("JSON deserialization failed - there are no customers preferences.");
            if (regions == null || regions.isEmpty())
                throw new Exception("JSON deserialization failed - there are no regions.");


            // Get the JADE runtime interface (singleton)
            jade.core.Runtime runtime = jade.core.Runtime.instance();
            // Create profile for main container and start rma agent that is responsible for gui
            Profile mainProfile = new ProfileImpl();
            mainProfile.setParameter(Profile.MAIN_HOST, "localhost");
            ContainerController mainContainer = runtime.createMainContainer(mainProfile);
            AgentController rma = mainContainer.createNewAgent("rma", "jade.tools.rma.rma", null);
            rma.start();

            // Create profiles for bars containers and start bars agents
            for (BarData bar : bars) {
                Bar modelBar = Map(bar);

                if (modelBar != null) {
                    Profile barContainerProfile = new ProfileImpl();
                    barContainerProfile.setParameter(Profile.MAIN_HOST, "localhost");
                    barContainerProfile.setParameter(Profile.CONTAINER_NAME, "Bar_container_" + modelBar.getName());
                    AgentContainer barContainer = runtime.createAgentContainer(barContainerProfile);
                    AgentController ba = barContainer.createNewAgent("bar_" + modelBar.getName(),
                            BAR_AGENT_CLASS_PATH,
                            new Object[]{modelBar}); // Arguments
                    ba.start();

                    if (bar.isLoudnessController()) {
                        AgentController lc = barContainer.createNewAgent(
                                BarFinderAgentNameUtils.GetBarControllerName(modelBar.getName(), LOUDNESS_CONTROLLER_AGENT_NAME),
                                LOUDNESS_CONTROLLER_AGENT_CLASS_PATH,
                                new Object[]{}); // Arguments
                        lc.start();
                    }

                    if (bar.isSeatsController()) {
                        AgentController sc = barContainer.createNewAgent(
                                BarFinderAgentNameUtils.GetBarControllerName(modelBar.getName(), SEATS_CONTROLLER_AGENT_NAME),
                                SEATS_CONTROLLER_AGENT_CLASS_PATH,
                                new Object[]{modelBar.getSeatsNumber()}); // Arguments
                        sc.start();
                    }

                    AgentController rc = barContainer.createNewAgent(
                            BarFinderAgentNameUtils.GetBarControllerName(modelBar.getName(), RESOURCES_CONTROLLER_AGENT_NAME),
                            RESOURCES_CONTROLLER_AGENT_CLASS_PATH,
                            new Object[]{modelBar.getBeers()}); // Arguments
                    rc.start();
                }
            }

            // Create profile for customer container
            Profile customerContainerProfile = new ProfileImpl();
            customerContainerProfile.setParameter(Profile.MAIN_HOST, "localhost");
            customerContainerProfile.setParameter(Profile.CONTAINER_NAME, "Customers_container");
            AgentContainer customerContainer = runtime.createAgentContainer(customerContainerProfile);
            // Start customers agents
            for (PreferencesData preferencesData : preferences) {
                Preferences modelPreferences = Map(preferencesData);

                if (modelPreferences != null) {
                    AgentController ca = customerContainer.createNewAgent("customer_" + preferencesData.getCustomer(),
                            CUSTOMER_AGENT_CLASS_PATH,
                            new Object[]{modelPreferences, regions}); // Arguments
                    ca.start();
                }
            }
        }
        catch (StaleProxyException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
