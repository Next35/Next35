package nl.next35.logic.services;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.*;
import nl.next35.logic.PropertiesLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;


/**
 * @author Oscar de Leeuw
 */
public class AlchemyService {

    private final AlchemyLanguage service;
    private Map<String, Object> input;

    public AlchemyService() {
        Properties properties = PropertiesLoader.properties();
        this.service = new AlchemyLanguage(properties.getProperty(ServiceConstants.ALCHEMY_KEY));
    }

    public void setInput(String text) {
        this.input = new HashMap<>();
        input.put(AlchemyLanguage.TEXT, text);
    }

    public Concepts getConcepts() {
        return service.getConcepts(input).execute();
    }

    public DocumentEmotion.Emotion getEmotion() {
        return service.getEmotion(input).execute().getEmotion();
    }

    public Entities getEntities() {
        return service.getEntities(input).execute();
    }

    public Keywords getKeywords() {
        return service.getKeywords(input).execute();
    }

    public Sentiment getSentiment() {
        return service.getSentiment(input).execute().getSentiment();
    }
}
