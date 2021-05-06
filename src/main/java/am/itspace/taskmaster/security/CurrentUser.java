package am.itspace.taskmaster.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class CurrentUser extends User {

    private final am.itspace.taskmaster.entities.User user;

    public CurrentUser(am.itspace.taskmaster.entities.User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getType().name()));
        this.user = user;
    }

    public am.itspace.taskmaster.entities.User getUser() {
        return user;
    }
}
