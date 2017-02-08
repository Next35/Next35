package nl.next35.web;

import nl.next35.logic.RedditResolver;
import nl.next35.logic.ResolverChain;
import nl.next35.logic.TwitterResolver;
import nl.next35.logic.services.AlchemyService;
import nl.next35.logic.services.PersonalityService;
import nl.next35.logic.services.ToneService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lesley
 */
@SpringBootApplication
public class Main {

    private static final ResolverChain chain = new ResolverChain();

    private static final AlchemyService alchemy = new AlchemyService();
    private static final PersonalityService personality = new PersonalityService();
    private static final ToneService tone = new ToneService();

    public static void main(String[] args) {
        chain.add(new RedditResolver());
        chain.add(new TwitterResolver());
        SpringApplication.run(Main.class, args);
    }

    public static ResolverChain getResolverChain() {
        return chain;
    }

    public static AlchemyService getAlchemyService() {
        return alchemy;
    }

    public static PersonalityService getPersonalityService() {
        return personality;
    }

    public static ToneService getToneService() {
        return tone;
    }

}
