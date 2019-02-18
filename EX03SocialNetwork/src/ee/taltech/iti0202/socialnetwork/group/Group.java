package ee.taltech.iti0202.socialnetwork.group;
import ee.taltech.iti0202.socialnetwork.message.Message;
import ee.taltech.iti0202.socialnetwork.user.User;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Group {
    private User owner;
    private String name;
    private HashSet<User> groupMembers = new HashSet<>();
    private List<Message> groupMessages = new LinkedList<>();

    public Group(String groupName, User groupOwner) {
        owner = groupOwner;
        name = groupName;
        groupMembers.add(owner);
        owner.addGroup(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public User getOwner() {
        return owner;
    }

    public void addUser(User user) {
        groupMembers.add(user);
        user.addGroup(this);
    }

    public Set<User> getParticipants() {
        return groupMembers;
    }

    public void publishMessage(Message message) {
        if (groupMembers.contains(message.getAuthor())) {
            groupMessages.add(message);
        }
    }

    public List<Message> getMessages() {
        return groupMessages;
    }

}