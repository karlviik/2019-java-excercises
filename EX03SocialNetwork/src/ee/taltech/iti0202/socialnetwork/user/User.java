package ee.taltech.iti0202.socialnetwork.user;

import ee.taltech.iti0202.socialnetwork.group.Group;

import java.util.HashSet;
import java.util.Set;

public class User {
    private String name;
    private Integer age = null;
    private HashSet<Group> groups = new HashSet<>();

    public User(String userName) {
        name = userName;
    }

    public User(String userName, Integer userAge) {
        name = userName;
        age = userAge;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

}
