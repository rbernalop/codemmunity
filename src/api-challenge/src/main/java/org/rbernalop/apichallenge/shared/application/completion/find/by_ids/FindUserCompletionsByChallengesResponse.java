package org.rbernalop.apichallenge.shared.application.completion.find.by_ids;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.rbernalop.apichallenge.completion.domain.aggregate.Completion;
import org.rbernalop.apichallenge.completion.domain.value_object.CompletionId;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class FindUserCompletionsByChallengesResponse implements Response {
    private Map<CompletionId, CompletionResponse> completions;

    public static FindUserCompletionsByChallengesResponse from(List<Completion> completions) {
        Map<CompletionId, CompletionResponse> completionsMap = completions.stream().collect(
            Collectors.toMap(
                Completion::getCompletionId,
                completion -> CompletionResponse.from(completion.getScriptContent(), completion.getLanguageName())
            )
        );

        return new FindUserCompletionsByChallengesResponse(completionsMap);
    }
}
