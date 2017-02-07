package nl.next35.logic.services;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyLanguage;


/**
 * @author Oscar de Leeuw
 */
public class AlchemyService {

    private final AlchemyLanguage service;

    public AlchemyService() {
        this.service = new AlchemyLanguage(ServiceConstants.ALCHEMY_KEY);
    }

    public AlchemyLanguage getService() {
        return service;
    }
}
