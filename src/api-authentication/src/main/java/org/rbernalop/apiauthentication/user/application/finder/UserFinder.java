package org.rbernalop.apiauthentication.user.application.finder;

import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameResponse;
import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.apiauthentication.user.domain.service.DomainUserFinder;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;

public class UserFinder extends UseCase {

    private final DomainUserFinder domainUserFinder;

    public UserFinder(QueryBus queryBus, EventBus eventBus, UserRepository userRepository) {
        super(queryBus, eventBus);
        this.domainUserFinder = new DomainUserFinder(userRepository);
    }

    public FindUserByUsernameResponse findUserByUsername(UserUsername username) {
        User user = domainUserFinder.findByUsername(username);
        assert user.getId() != null;
        return new FindUserByUsernameResponse(
            user.getId().getValue(),
            user.getUsername(),
            user.getEmail(),
            user.getPassword()
        );
    }
}
