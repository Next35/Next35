package nl.next35.web;

import nl.next35.domain.User;
import nl.next35.logic.ResolverChain;
import nl.next35.logic.TwitterResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lesley
 */
@SpringBootApplication
public class Main {

    private static final ResolverChain chain = new ResolverChain();

    public static void main(String[] args) {
        // chain.add(new RedditResolver());
        chain.add(new TwitterResolver());
        chain.resolve(new User("fastmike97"));
        SpringApplication.run(Main.class, args);
    }

    public static ResolverChain getResolverChain() {
        return chain;
    }

}
