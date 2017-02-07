package nl.next35.logic.services;

import com.ibm.watson.developer_cloud.personality_insights.v2.PersonalityInsights;

/**
 * @author Oscar de Leeuw
 */
public class PersonalityService {

    private final PersonalityInsights service;

    public PersonalityService() {
        this.service = new PersonalityInsights(ServiceConstants.PERSONALITY_USERNAME, ServiceConstants.PERSONALITY_PASSWORD);
    }

    public PersonalityInsights getService() {
        return service;
    }
}
