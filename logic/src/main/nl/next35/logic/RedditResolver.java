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

/**
 * Created by ken on 2/7/2017.
 */
public class RedditResolver implements Resolver {

    public static final String USER_AGENT = "Bot for reading comments of users";
    private URL url;
    private ArrayList<Post> posts;

    public RedditResolver() {

    }

    @Override
    public ArrayList<Post> getPosts(User user) {
        posts = new ArrayList<>();
        InputStream in = null;
        JSONParser parser = new JSONParser();
        try {
            this.url = new URL("https://www.reddit.com/user/" + user.getUsername() + "/comments.json");
            //in = this.url.openStream();
            URLConnection conn = this.url.openConnection();
            Thread.sleep(2000);
            conn.setRequestProperty("User-Agent", USER_AGENT);
            in = conn.getInputStream();
            String jsonOutput = IOUtils.toString(in);
            System.out.println("jsonoutput:    " + jsonOutput);
            // parse json
            Object obj = parser.parse(jsonOutput);
            JSONObject jsonObject = (JSONObject) obj;
            // loop array
            JSONObject msg = (JSONObject) jsonObject.get("data");
            JSONArray children = (JSONArray) msg.get("children");
            for (int i = 0; i < children.size(); i++) {
                JSONObject postObj = (JSONObject) children.get(i);
                JSONObject postData = (JSONObject) postObj.get("data");
                String msgBody = postData.get("body").toString();
                Post p = new Post(user, msgBody);
                posts.add(p);
            }
        } catch (IOException | ParseException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(in);
        }
        return posts;
    }
}
