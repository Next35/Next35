package nl.next35.web.controller;

import nl.next35.domain.Post;
import nl.next35.domain.User;
import nl.next35.logic.ResolverChain;
import nl.next35.logic.services.AlchemyService;
import nl.next35.logic.services.PersonalityService;
import nl.next35.logic.services.ToneService;
import nl.next35.web.Main;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lesley
 */
@RestController
public final class UserController {

    @RequestMapping("/user/resolve")
    public List<Post> resolve(@RequestParam(value = "name") String name, @RequestParam(value = "service") String service) {
        ResolverChain chain = Main.getResolverChain();

        List<Post> posts = new ArrayList<>();
        posts.addAll(chain.resolve(new User(name)));

        return posts;
    }

    @RequestMapping("/user/alchemy")
    public JSONObject alchemy(@RequestParam("name") String name) {
        ResolverChain chain = Main.getResolverChain();

        List<Post> posts = new ArrayList<>();
        posts.addAll(chain.resolve(new User(name)));

        StringBuilder builder = new StringBuilder();
        for (Post p : posts) {
            builder.append(p.getContent());
            builder.append("\n");
        }

        AlchemyService service = Main.getAlchemyService();
        service.setInput(builder.toString());

        JSONObject json = new JSONObject();
        json.put("emotion", service.getEmotion());
        json.put("concepts", service.getConcepts());
        json.put("sentiment", service.getSentiment());
        json.put("keywords", service.getKeywords());
        json.put("entities", service.getEntities());

        return json;
    }

    @RequestMapping("/user/personality")
    public JSONObject personality(@RequestParam("name") String name) {
        ResolverChain chain = Main.getResolverChain();

        List<Post> posts = new ArrayList<>();
        posts.addAll(chain.resolve(new User(name)));

        StringBuilder builder = new StringBuilder();
        for (Post p : posts) {
            builder.append(p.getContent());
            builder.append("\n");
        }

        PersonalityService service = Main.getPersonalityService();
        service.setInput(builder.toString());

        JSONObject json = new JSONObject();
        json.put("personality", service.getProfile());

        return json;
    }

    @RequestMapping("/user/tone ")
    public JSONObject tone(@RequestParam("name") String name) {
        ResolverChain chain = Main.getResolverChain();

        List<Post> posts = new ArrayList<>();
        posts.addAll(chain.resolve(new User(name)));

        StringBuilder builder = new StringBuilder();
        for (Post p : posts) {
            builder.append(p.getContent());
            builder.append("\n");
        }

        ToneService service = Main.getToneService();
        service.setInput(builder.toString());

        JSONObject json = new JSONObject();
        json.put("document", service.getDocumentTone());

        return json;
    }

    @RequestMapping("/user/*")
    public String user() {
        return "Invalid call.";
    }

}
