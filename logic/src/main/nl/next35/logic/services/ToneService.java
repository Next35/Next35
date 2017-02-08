package nl.next35.logic.services;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ElementTone;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.SentenceTone;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneOptions;
import nl.next35.logic.PropertiesLoader;

import java.util.List;
import java.util.Properties;

/**
 * @author Oscar de Leeuw
 */
public class ToneService {

    private final ToneAnalyzer service;
    private String input;

    public ToneService() {
        Properties properties = PropertiesLoader.properties();
        this.service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19, properties.getProperty(ServiceConstants.TONE_USERNAME), properties.getProperty(ServiceConstants.TONE_PASSWORD));
    }

    public void setInput(String text) {
        input = text;
    }

    public ElementTone getDocumentTone() {
        return service.getTone(input, new ToneOptions.Builder().build()).execute().getDocumentTone();
    }

    public List<SentenceTone> getSentenceTone() {
        return service.getTone(input, new ToneOptions.Builder().build()).execute().getSentencesTone();
    }
}
