package nl.next35.logic;

import nl.next35.domain.Post;
import nl.next35.domain.User;
import twitter4j.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 08/2/2017.
 */
public class TwitterResolver implements Resolver {
    @Override
    public List<Post> getPosts(User user) {
        Twitter twitter = new TwitterFactory().getInstance();

        int pageno = 1;
        List<Status> statuses = new ArrayList();

        while (true) {
            try {
                int size = statuses.size();
                Paging page = new Paging(pageno++, 100);

                statuses.addAll(twitter.getUserTimeline(user.getUsername(), page));
                if (statuses.size() == size) {
                    break;
                }
            } catch (TwitterException e) {
                e.printStackTrace();
                break;
            }
        }
        List<Post> posts = new ArrayList<>();
        for (Status status : statuses) {
            if (!status.isRetweet()) {
                Post post = new Post(user, status.getText());
                posts.add(post);
            }
        }
        return posts;
    }
}
