package nl.next35.logic.services;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;
import com.ibm.watson.developer_cloud.alchemy.v1.model.*;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Oscar de Leeuw
 */
public class AlchemyService {

    private final AlchemyLanguage service;
    private Map<String, Object> input;

    public AlchemyService() {
        this.service = new AlchemyLanguage(ServiceConstants.ALCHEMY_KEY);
    }

    public void setInput(String text) {
        this.input = new HashMap<>();
        input.put(AlchemyLanguage.TEXT, text);
    }

    public Concepts getConcepts() {
        return service.getConcepts(input).execute();
    }

    public DocumentEmotion getEmotion() {
        return service.getEmotion(input).execute();
    }

    public Entities getEntities() {
        return service.getEntities(input).execute();
    }

    public Keywords getKeywords() {
        return service.getKeywords(input).execute();
    }

    public DocumentSentiment getSentiment() {
        return service.getSentiment(input).execute();
    }
}
