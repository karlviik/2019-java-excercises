package ee.taltech.iti0202.socialnetwork.feed;

import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Set;

public class Feed {
    private User user;
    private Set<Message> feedMessageSet = new HashSet<>();

    public Feed(User feedUser, Set<Message> messages) {
        user = feedUser;
        if (messages != null) feedMessageSet.addAll(messages);
    }

    public User getUser() {
        return user;
    }

    public void buildFeed() {
        for (Group group : user.getGroups()) {
            feedMessageSet.addAll(group.getMessages());
        }
    }

    public Set<Message> getMessages() {
        return feedMessageSet;
    }
}
