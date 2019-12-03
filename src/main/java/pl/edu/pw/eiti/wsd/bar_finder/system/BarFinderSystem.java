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

import static pl.edu.pw.eiti.wsd.bar_finder.utilities.JsonUtils.GetBars;

public class BarFinderSystem
{
    public static void main(String[] args) throws Exception {
        if (args == null || args.length != 1)
            throw new Exception("Wrong arguments!");

        String barsFilePath = args[0];

        if (barsFilePath == null || barsFilePath.isEmpty())
            throw new Exception("Empty filepath that should contain bars data.");

        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(barsFilePath)) {
            JSONObject obj = (JSONObject)jsonParser.parse(reader);

            if (obj == null)
                throw new Exception("JSON parsing failed.");

            JSONArray barsJSONArray = (JSONArray)obj.get("bars");

            if (barsJSONArray.isEmpty())
                throw new Exception("Provided file doesn't contain any bars data.");

            List<BarData> bars = GetBars(barsJSONArray);

            if (bars == null || bars.isEmpty())
                throw new Exception("JSON deserialization failed - there are no bars.");

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
                barContainerProfile.setParameter(Profile.CONTAINER_NAME, "Bar-container-" + bar.getName());
                AgentContainer barContainer = runtime.createAgentContainer(barContainerProfile);
                // Create rma agent that is responsible for gui
                AgentController ag = barContainer.createNewAgent("bar-" + bar.getName(),
                        "pl.edu.pw.eiti.wsd.bar_finder.bar_agent.BarAgent",
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
