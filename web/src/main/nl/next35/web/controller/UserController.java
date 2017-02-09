package nl.next35.web.controller;

import com.ibm.watson.developer_cloud.alchemy.v1.model.Concept;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Concepts;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Entities;
import com.ibm.watson.developer_cloud.alchemy.v1.model.Entity;
import com.ibm.watson.developer_cloud.personality_insights.v2.model.Trait;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ElementTone;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneCategory;
import com.ibm.watson.developer_cloud.tone_analyzer.v3.model.ToneScore;
import nl.next35.domain.Post;
import nl.next35.domain.User;
import nl.next35.domain.util.PostUtil;
import nl.next35.logic.ResolverChain;
import nl.next35.logic.services.AlchemyService;
import nl.next35.logic.services.PersonalityService;
import nl.next35.logic.services.ToneService;
import nl.next35.web.Main;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @CrossOrigin
    @RequestMapping("/user/alchemy")
    public JSONObject alchemy(@RequestParam("name") String name) {
        ResolverChain chain = Main.getResolverChain();
        List<Post> posts = chain.resolve(new User(name));
        String postsString = PostUtil.postCollectionToString(posts);

        AlchemyService service = Main.getAlchemyService();
        service.setInput(postsString);

        JSONObject json = new JSONObject();
        json.put("emotion", service.getEmotion());
        json.put("concepts", service.getConcepts());
        json.put("sentiment", service.getSentiment());
        json.put("keywords", service.getKeywords());
        json.put("entities", service.getEntities());

        return json;
    }

    @CrossOrigin
    @RequestMapping("/user/alchemy/concepts")
    public JSONObject alchemyConcepts(@RequestParam("name") String name) {
        ResolverChain chain = Main.getResolverChain();
        List<Post> posts = chain.resolve(new User(name));
        String postsString = PostUtil.postCollectionToString(posts);

        System.out.println(posts.size());
        System.out.println(postsString.length());

        AlchemyService service = Main.getAlchemyService();
        service.setInput(postsString);

        Map<String, Double> data = new HashMap<>();
        Concepts concepts = service.getConcepts();

        for (Concept concept : concepts.getConcepts()) {
            data.put(concept.getText(), concept.getRelevance());
        }

        Entities entities = service.getEntities();

        for (Entity entity : entities.getEntities()) {
            data.put(entity.getText(), entity.getRelevance());
        }

        JSONObject json = new JSONObject();
        json.put("concepts", data);
        return json;
    }

    @CrossOrigin
    @RequestMapping("/user/personality")
    public JSONObject personality(@RequestParam("name") String name) {
        ResolverChain chain = Main.getResolverChain();
        List<Post> posts = chain.resolve(new User(name));
        String postsString = PostUtil.postCollectionToString(posts);

        PersonalityService service = Main.getPersonalityService();
        service.setInput(postsString);

        Map<String, Double> traits = new HashMap<>();
        List<Trait> children = service.getProfile().getTree().getChildren();
        children.forEach(c -> processPersonality(c, traits));

        JSONObject json = new JSONObject();
        json.put("personality", traits);
        return json;
    }

    private void processPersonality(Trait trait, Map<String, Double> traits) {
        List<Trait> children = trait.getChildren();
        if (children != null) {
            children.forEach(c -> processPersonality(c, traits));
        }

        String name = trait.getName();
        Double percentage = trait.getPercentage();
        if (name != null && percentage != null && percentage > 0) {
            traits.put(name, percentage);
        }
    }

    @CrossOrigin
    @RequestMapping("/user/tone")
    public JSONObject tone(@RequestParam("name") String name) {
        ResolverChain chain = Main.getResolverChain();
        List<Post> posts = chain.resolve(new User(name));
        String postsString = PostUtil.postCollectionToString(posts);

        ToneService service = Main.getToneService();
        service.setInput(postsString);

        Map<String, Double> emotions = new HashMap<>();
        ElementTone tone = service.getDocumentTone();
        for (ToneCategory category : tone.getTones()) {
            for (ToneScore score : category.getTones()) {
                String toneName = score.getName();
                Double percentage = score.getScore();
                if (toneName != null && percentage != null && percentage > 0) {
                    emotions.put(toneName, percentage);
                }
            }
        }

        JSONObject json = new JSONObject();
        json.put("tone", emotions);
        return json;
    }

    @RequestMapping("/user/*")
    public String user() {
        return "Invalid call.";
    }

}
