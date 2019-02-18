package ee.taltech.iti0202.socialnetwork.message;

import ee.taltech.iti0202.socialnetwork.user.User;

public class Message {
    private String title;
    private String message;
    private User author;

    public Message(String messageTitle, String messageContents, User messageAuthor) {
        title = messageTitle;
        message = messageContents;
        author = messageAuthor;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public User getAuthor() {
        return author;
    }
}
