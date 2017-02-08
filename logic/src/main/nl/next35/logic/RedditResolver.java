package nl.next35.logic;

import nl.next35.domain.Post;
import nl.next35.domain.User;
import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ken on 2/7/2017.
 */
public final class RedditResolver implements Resolver {

    private static final String USER_AGENT = "Bot for reading comments of users";

    @Override
    public List<Post> getPosts(User user) {
        List<Post> posts = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            URL url = new URL("https://www.reddit.com/user/" + user.getUsername() + "/comments.json?limit=100");
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("User-Agent", USER_AGENT);

            try (InputStream in = connection.getInputStream()) {
                String value = IOUtils.toString(in);
                JSONObject json = (JSONObject) parser.parse(value);
                JSONObject message = (JSONObject) json.get("data");
                JSONArray children = (JSONArray) message.get("children");
                for (int i = 0; i < children.size(); i++) {
                    JSONObject post = (JSONObject) children.get(i);
                    JSONObject content = (JSONObject) post.get("data");

                    String body = content.get("body").toString();
                    posts.add(new Post(user, body));
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

        return posts;
    }
}
