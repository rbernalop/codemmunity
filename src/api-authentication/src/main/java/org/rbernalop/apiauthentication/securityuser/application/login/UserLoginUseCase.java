package org.rbernalop.apiauthentication.securityuser.application.login;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apiauthentication.securityuser.domain.port.TokenGenerator;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameQuery;
import org.rbernalop.apiauthentication.shared.application.user.finder.username.FindUserByUsernameResponse;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;

public class UserLoginUseCase extends UseCase {
    private final TokenGenerator tokenGenerator;

    public UserLoginUseCase(TokenGenerator tokenGenerator,
                            QueryBus queryBus) {
        super(queryBus);
        this.tokenGenerator = tokenGenerator;
    }

    public UserLoginResponse login(String username, String password) {
        String token = tokenGenerator.generateToken(username, password);
        FindUserByUsernameResponse user = this.ask(new FindUserByUsernameQuery(username));
        return new UserLoginResponse(
            token,
            user.getId(),
            user.getUsername(),
            user.getEmail()
        );
    }
}

@AllArgsConstructor
@Getter
@Setter
class UserLoginResponse {
    private String token;
    private String id;
    private String username;
    private String email;
}