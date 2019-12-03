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

import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.BarData.BARS_KEY;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.PreferencesData.CUSTOMERS_PREFERENCES_KEY;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JsonUtils.*;

public class BarFinderSystem
{
    public static void main(String[] args) throws Exception {
        if (args == null || args.length != 2)
            throw new Exception("Wrong given arguments count!");

        String barsFilePath = args[0];
        String preferencesFilePath = args[1];

        if (barsFilePath == null || barsFilePath.isEmpty())
            throw new Exception("Empty filepath that should contain bars data.");

        if (preferencesFilePath == null || preferencesFilePath.isEmpty())
            throw new Exception("Empty filepath that should contain customers preferences data.");

        JSONParser jsonParser = new JSONParser();

        try (FileReader barsFileReader = new FileReader(barsFilePath);
             FileReader preferencesFileReader = new FileReader(preferencesFilePath)) {
            JSONObject barsJSONObj = (JSONObject)jsonParser.parse(barsFileReader);
            JSONObject preferencesJSONObj = (JSONObject)jsonParser.parse(preferencesFileReader);

            if (barsJSONObj == null)
                throw new Exception("JSON parsing failed for bars data.");
            if (preferencesJSONObj == null)
                throw new Exception("JSON parsing failed for customers preferences data.");

            JSONArray barsJSONArray = (JSONArray)barsJSONObj.get(BARS_KEY);
            JSONArray preferencesJSONArray = (JSONArray)preferencesJSONObj.get(CUSTOMERS_PREFERENCES_KEY);

            if (barsJSONArray.isEmpty())
                throw new Exception("Provided file doesn't contain any bars data.");
            if (preferencesJSONArray.isEmpty())
                throw new Exception("Provided file doesn't contain any customers preferences data.");

            List<BarData> bars = GetBars(barsJSONArray);
            List<PreferencesData> preferences = GetCustomersPreferences(preferencesJSONArray);

            if (bars == null || bars.isEmpty())
                throw new Exception("JSON deserialization failed - there are no bars.");
            if (preferences == null || preferences.isEmpty())
                throw new Exception("JSON deserialization failed - there are no customers preferences.");

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
                Profile barContainerProfile = new ProfileImpl();
                barContainerProfile.setParameter(Profile.MAIN_HOST, "localhost");
                barContainerProfile.setParameter(Profile.CONTAINER_NAME, "Bar_container_" + bar.getName());
                AgentContainer barContainer = runtime.createAgentContainer(barContainerProfile);
                AgentController ag = barContainer.createNewAgent("bar_" + bar.getName(),
                        "pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent",
                        new Object[]{}); // Arguments
                ag.start();
            }

            // Create profile for customer container
            Profile customerContainerProfile = new ProfileImpl();
            customerContainerProfile.setParameter(Profile.MAIN_HOST, "localhost");
            customerContainerProfile.setParameter(Profile.CONTAINER_NAME, "Customers_container");
            AgentContainer customerContainer = runtime.createAgentContainer(customerContainerProfile);
            // Start customers agents
            for (PreferencesData preferencesData : preferences) {
                AgentController ag = customerContainer.createNewAgent("customer_" + preferencesData.getCustomer(),
                        "pl.edu.pw.eiti.wsd.bar_finder.customer_agent.CustomerAgent",
                        new Object[]{}); // Arguments
                ag.start();
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
