package org.rbernalop.apiauthentication.user.application.create;

import org.rbernalop.apiauthentication.user.domain.aggregate.User;
import org.rbernalop.apiauthentication.user.domain.exception.InvalidCaptchaException;
import org.rbernalop.apiauthentication.user.domain.exception.UserAlreadyExistsException;
import org.rbernalop.apiauthentication.user.domain.port.CaptchaVerifier;
import org.rbernalop.apiauthentication.user.domain.port.UserRepository;
import org.rbernalop.apiauthentication.user.domain.service.DomainUserSearcher;
import org.rbernalop.apiauthentication.user.domain.value_object.*;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserCreator extends UseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final CaptchaVerifier captchaVerifier;

    public UserCreator(QueryBus queryBus, UserRepository userRepository, PasswordEncoder passwordEncoder,
                       CaptchaVerifier captchaVerifier) {
        super(queryBus);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.captchaVerifier = captchaVerifier;
    }

    public void create(UserId id, UserName name, UserSurname surname, UserUsername username, UserEmail email,
                       UserPassword password, UserBirthDate birthDate, String captchaToken) {
        DomainUserSearcher domainUserSearcher = new DomainUserSearcher(userRepository);

        if (domainUserSearcher.findById(id) != null) {
            throw new UserAlreadyExistsException("User with id " + id.getId() + " already exists");
        }

        if (domainUserSearcher.findByUsername(username) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        if (domainUserSearcher.findByEmail(email) != null) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        if (!captchaVerifier.verifyCaptcha(captchaToken)) {
            throw new InvalidCaptchaException("Captcha verification failed");
        }

        UserPasswordEncrypted passwordEncrypted = new UserPasswordEncrypted(passwordEncoder.encode(password.getValue()));
        User user = new User(id, name, surname, username, email, passwordEncrypted, birthDate);
        userRepository.save(user);
    }
}
