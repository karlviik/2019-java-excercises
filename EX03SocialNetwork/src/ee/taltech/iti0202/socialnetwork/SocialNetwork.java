package ee.taltech.iti0202.socialnetwork;
import ee.taltech.iti0202.socialnetwork.feed.Feed;
import ee.taltech.iti0202.socialnetwork.group.Group;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.Set;

public class SocialNetwork {
    private Set<Group> socialNetworkGroups = new HashSet<>();

    public void registerGroup(Group group) {
        socialNetworkGroups.add(group);
    }

    public Set<Group> getGroups() {
        return socialNetworkGroups;
    }

    public Feed getFeedForUser(User user) {
        Feed userFeed = new Feed(user, null);
        userFeed.buildFeed();
        return userFeed;
    }

}
