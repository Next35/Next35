package nl.next35.logic.services;

import com.ibm.watson.developer_cloud.tone_analyzer.v3.ToneAnalyzer;

/**
 * @author Oscar de Leeuw
 */
public class ToneService {

    private final ToneAnalyzer service;

    public ToneService() {
        this.service = new ToneAnalyzer(ToneAnalyzer.VERSION_DATE_2016_05_19, ServiceConstants.TONE_USERNAME, ServiceConstants.TONE_PASSWORD);
    }

    public ToneAnalyzer getService() {
        return service;
    }
}
