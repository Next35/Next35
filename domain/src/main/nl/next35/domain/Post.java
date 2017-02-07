package nl.next35.domain;

/**
 * @author Lesley
 */
public class Post {

    private final User user;
    private final String content;

    public Post(User user, String content) {
        this.user = user;
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

}
