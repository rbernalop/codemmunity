package org.rbernalop.apichallenge.completion.application.find.by_ids;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.rbernalop.apichallenge.completion.domain.aggregate.Completion;
import org.rbernalop.apichallenge.completion.domain.aggregate.CompletionMother;
import org.rbernalop.apichallenge.completion.domain.port.CompletionRepository;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.CompletionResponse;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByIdsQuery;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByChallengesIdsQueryMother;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByIdsResponse;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.rbernalop.shared.infrastructure.testing.UnitTestCase;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FindUserCompletionsByIdsQueryHandlerTest extends UnitTestCase {
    @Mock
    private CompletionRepository completionRepository;

    @InjectMocks
    private FindUserCompletionsByIdsQueryHandler findUserCompletionsByIdsQueryHandler;

    @Test
    void should_find_user_completions_by_challenges_ids() {
        // GIVEN
        Completion completion = CompletionMother.random();
        FindUserCompletionsByIdsQuery query =
            FindUserCompletionsByChallengesIdsQueryMother.fromCompletionId(completion.getCompletionId());

        ChallengeId challengeId = new ChallengeId(completion.getChallengeId());
        UserUsername userUsername = new UserUsername(completion.getUserUsername());
        when(completionRepository.findByChallengeIdInAndUserUsername(List.of(challengeId), userUsername))
            .thenReturn(List.of(completion));

        // WHEN
        FindUserCompletionsByIdsResponse response =
            assertDoesNotThrow(() -> findUserCompletionsByIdsQueryHandler.handle(query));

        // THEN
        verify(completionRepository, times(1)).findByChallengeIdInAndUserUsername(any(), any());
        assertEquals(1, response.getCompletions().size());
        CompletionResponse responseCompletion = response.getCompletions().get(completion.getChallengeId());
        assertEquals(completion.getScriptContent(), responseCompletion.getScriptContent());
        assertEquals(completion.getLanguageName(), responseCompletion.getLanguageName());
    }
}
