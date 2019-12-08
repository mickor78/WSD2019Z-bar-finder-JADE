package pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.CFReflectiveIntrospector;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PrimitiveSchema;

import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.Preferences;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.PreferencesParameter;
import pl.edu.pw.eiti.wsd.bar_finder.commons.model_structures.vocabulary.PreferencesVocabulary;

public class PreferencesOntology extends Ontology implements PreferencesVocabulary {

    public static final String PREFERENCES_ONTOLOGY_NAME = "Preferences_ontology";

    private static Ontology instance = new PreferencesOntology();

    public static Ontology getInstance() {
        return instance;
    }

    private PreferencesOntology() {
        super(PREFERENCES_ONTOLOGY_NAME, BasicOntology.getInstance(), new CFReflectiveIntrospector());

        try {
            ConceptSchema pps = new ConceptSchema(PREFERENCES_PARAMETER);
            add(pps, PreferencesParameter.class);
            pps.add(PREFERENCES_PARAMETER_NAME, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            pps.add(PREFERENCES_PARAMETER_VALUE, (PrimitiveSchema) getSchema(BasicOntology.STRING), ObjectSchema.MANDATORY);
            pps.add(PREFERENCES_PARAMETER_IMPORTANCE, (PrimitiveSchema) getSchema(BasicOntology.FLOAT), ObjectSchema.MANDATORY);

            ConceptSchema ps = new ConceptSchema(PREFERENCES);
            add(ps, Preferences.class);
            ps.add(PREFERENCES_PARAMETERS, (ConceptSchema) getSchema(PREFERENCES_PARAMETER), 1, ObjectSchema.UNLIMITED);
        }
        catch (OntologyException ex) {
            // TODO:
            ex.printStackTrace();
        }
    }
}
