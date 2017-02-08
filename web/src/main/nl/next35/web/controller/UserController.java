package nl.next35.web.controller;

import nl.next35.domain.Post;
import nl.next35.domain.User;
import nl.next35.logic.ResolverChain;
import nl.next35.web.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Lesley
 */
@RestController
public final class UserController {

    @Autowired
    private ApplicationContext context;


    @RequestMapping("/user/resolve")
    public List<Post> resolve(@RequestParam(value = "name") String name, @RequestParam(value = "service") String service) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(service);

        ResolverChain chain = Main.getResolverChain();

        List<Post> posts = new ArrayList<>();
        posts.addAll(chain.resolve(new User(name)));

        return posts;
    }

    @RequestMapping("/user/*")
    public String user() {
        return "Invalid call.";
    }

}
