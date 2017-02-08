package nl.next35.logic;

import nl.next35.domain.Post;
import nl.next35.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Lesley
 */
public final class ResolverChain {

    private final List<Resolver> resolvers = new ArrayList<>();

    public List<Post> resolve(User user) {
        List<Post> posts = new ArrayList<>();

        resolvers.stream().map(r -> r.getPosts(user)).forEach(posts::addAll);

        return posts;
    }

    public void add(Resolver resolver) {
        resolvers.add(resolver);
    }

    public void remove(Resolver resolver) {
        resolvers.remove(resolver);
    }

}
