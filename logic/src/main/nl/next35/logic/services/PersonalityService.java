package nl.next35.logic.services;

import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Profile;
import nl.next35.logic.PropertiesLoader;

import java.util.Properties;

/**
 * @author Oscar de Leeuw
 */
public class PersonalityService {

    private final PersonalityInsights service;
    private String input;

    public PersonalityService() {
        Properties properties = PropertiesLoader.properties();
        this.service = new PersonalityInsights(properties.getProperty(ServiceConstants.PERSONALITY_USERNAME), properties.getProperty(ServiceConstants.PERSONALITY_PASSWORD));
    }

    public void setInput(String text) {
        input = text;
    }

    public Profile getProfile() {
        return service.getProfile(input).execute();
    }
}
