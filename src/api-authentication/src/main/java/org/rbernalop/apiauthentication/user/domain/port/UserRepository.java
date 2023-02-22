package org.rbernalop.apiauthentication.user.domain.port;


import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.value_object.UserEmail;
import org.rbernalop.apiauthentication.user.domain.value_object.UserId;
import org.rbernalop.apiauthentication.user.domain.value_object.UserUsername;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {
    Optional<User> findByUsername(UserUsername username);

    Optional<User> findByEmail(UserEmail email);
}
