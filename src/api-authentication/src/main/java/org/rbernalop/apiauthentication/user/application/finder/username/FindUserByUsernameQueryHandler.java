package org.rbernalop.apiauthentication.user.application.finder.username;

import lombok.extern.slf4j.Slf4j;
import org.rbernalop.apiauthentication.user.application.finder.UserFinder;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.apiauthentication.user.domain.value_object.UserUsername;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.bus.query.QueryHandler;

@Service
@Slf4j
public class FindUserByUsernameQueryHandler implements QueryHandler<FindUserByUsernameQuery, FindUserByUsernameResponse> {
    private final UserFinder userFinder;

    public FindUserByUsernameQueryHandler(UserRepository userRepository, QueryBus queryBus) {
        this.userFinder = new UserFinder(queryBus, userRepository);
    }

    @Override
    public FindUserByUsernameResponse handle(FindUserByUsernameQuery query) {
         log.info("Finding user with username: {}", query.getUsername());
        UserUsername username = new UserUsername(query.getUsername());
        return userFinder.findUserByUsername(username);
    }
}

