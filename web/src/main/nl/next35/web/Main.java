package nl.next35.web;

import nl.next35.domain.User;
import nl.next35.logic.RedditResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Lesley
 */
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        RedditResolver rdr = new RedditResolver();
        System.out.println(rdr.getPosts(new User("Peewbacca")));
    }

}
