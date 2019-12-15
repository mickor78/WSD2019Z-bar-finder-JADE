package pl.edu.pw.eiti.wsd.bar_finder.customer_agent;

import java.util.*;

import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.core.AID;

import pl.edu.pw.eiti.wsd.bar_finder.BarFinderAgent;
import pl.edu.pw.eiti.wsd.bar_finder.commons.input_structures.RegionData;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Preferences;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesDictionary;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.ontology.PreferencesOntology;
import pl.edu.pw.eiti.wsd.bar_finder.customer_agent.behaviours.FindBar;
import pl.edu.pw.eiti.wsd.bar_finder.utilities.ConsolePrintingMsgUtils;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.CoordinationUtils.*;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.mappers.PreferencesMapper.Map;


import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.BAR_AGENT;
import static pl.edu.pw.eiti.wsd.bar_finder.utilities.BarFinderConstants.CUSTOMER_AGENT;
import static pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.ParametersNames.LOCALIZATION_PARAM_NAME;

public class CustomerAgent extends BarFinderAgent {

    private final int MAX_OFFERS_NUMBER = 5;

    private Preferences preferences;
    private PreferencesDictionary preferencesDictionary;
    private List<RegionData> regions;

    private Codec codec = new SLCodec();
    private Ontology ontology = PreferencesOntology.getInstance();

    public Preferences getPreferences() {
        return preferences;
    }

    public Ontology getOntology() {
        return ontology;
    }

    public Codec getCodec() {
        return codec;
    }

    protected void setup() {
        ConsolePrintingMsgUtils.PrintMsg("Hello World! My name is " + getLocalName());

        // Get agent parameters
        Object[] args = getArguments();
        if (args != null && args.length > 0) {
            this.preferences = (Preferences) args[0];
            this.preferencesDictionary = Map(preferences);
            this.regions = (LinkedList<RegionData>)args[1];
        }


        if (preferences == null) {
            doDelete();
        } else {
            // Register agent
            String[] services = {CUSTOMER_AGENT};
            registerToService(services);

            // Register language and ontology
            getContentManager().registerLanguage(codec);
            getContentManager().registerOntology(ontology);

            addBehaviour(new FindBar());
        }
    }

    public List<AID> getBars() {
        List<AID> result = new ArrayList<>();
        TreeMap<Double, String> regionsDistances = new TreeMap<>();
        // Regions sorted by distance from customer
        for(RegionData region : regions){
            regionsDistances.put(getDistance(region.getLocalization(), preferencesDictionary.get(LOCALIZATION_PARAM_NAME).getValue()), region.getName());
        }

        int found_bars = 0;
        for(Map.Entry<Double, String> entry : regionsDistances.entrySet()){
            // Searching bars (one for every region), starting from closest regions
            AID barAgent = this.getService(BAR_AGENT + "_" + entry.getValue());
            if(barAgent != null){
                result.add(barAgent);
                found_bars++;
            }
            if(found_bars == MAX_OFFERS_NUMBER)
                break;
        }
        if(found_bars > 0)
            return result;
        else
            return null;
    }
}
