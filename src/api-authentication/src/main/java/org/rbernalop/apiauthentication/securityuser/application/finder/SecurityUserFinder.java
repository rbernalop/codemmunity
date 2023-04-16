package org.rbernalop.apiauthentication.securityuser.application.finder;

import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameQuery;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameResponse;
import org.rbernalop.shared.domain.SecurityUser;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service
public class SecurityUserFinder extends UseCase implements UserDetailsService {

    public SecurityUserFinder(QueryBus queryBus, EventBus eventBus) {
        super(queryBus, eventBus);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        FindUserByUsernameResponse user = ask(new FindUserByUsernameQuery(username));
        return new SecurityUser(user.getUsername(), user.getPassword());
    }
}
