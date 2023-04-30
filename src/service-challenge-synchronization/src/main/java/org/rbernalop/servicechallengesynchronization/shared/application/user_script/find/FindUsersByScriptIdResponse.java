package org.rbernalop.servicechallengesynchronization.shared.application.user_script.find;

import lombok.*;
import org.rbernalop.shared.domain.bus.query.Response;
import org.rbernalop.shared.domain.valueobject.UserUsername;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class FindUsersByScriptIdResponse implements Response {
    private List<String> usernames;

    public static FindUsersByScriptIdResponse fromUsernames(List<UserUsername> usernames) {
        List<String> usernamesStr = usernames.stream().map(UserUsername::getValue).toList();
        return new FindUsersByScriptIdResponse(usernamesStr);
    }
}
