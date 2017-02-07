package nl.next35.logic;

import nl.next35.domain.Post;
import nl.next35.domain.User;

import java.util.ArrayList;

/**
 * Created by ken on 2/7/2017.
 */
public interface Resolver {

    ArrayList<Post> getPosts(User user);

}
