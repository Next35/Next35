package nl.next35.domain.util;

import nl.next35.domain.Post;

import java.util.Collection;

/**
 * @author Lesley
 */
public final class PostUtil {

    private PostUtil() {
        throw new UnsupportedOperationException("Should not be instantiated.");
    }

    public static String postCollectionToString(Collection<Post> posts) {
        return posts.stream().map(Post::getContent).reduce("\n", String::concat);
    }

}
