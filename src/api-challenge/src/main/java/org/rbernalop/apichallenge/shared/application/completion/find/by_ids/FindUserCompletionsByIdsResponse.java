package org.rbernalop.apichallenge.shared.application.completion.find.by_ids;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.rbernalop.apichallenge.completion.domain.aggregate.Completion;
import org.rbernalop.shared.domain.bus.query.Response;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class FindUserCompletionsByIdsResponse implements Response {
    private Map<String, CompletionResponse> completions;

    public static FindUserCompletionsByIdsResponse from(List<Completion> completions) {
        Map<String, CompletionResponse> completionsMap = completions.stream().collect(
            Collectors.toMap(
                Completion::getChallengeId,
                completion -> CompletionResponse.from(completion.getScriptContent(), completion.getLanguageName(), completion.getUserUsername())
            )
        );

        return new FindUserCompletionsByIdsResponse(completionsMap);
    }
}
