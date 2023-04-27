package org.rbernalop.apichallenge.challenge.application.find;

import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByChallengesIdsQuery;
import org.rbernalop.apichallenge.shared.application.completion.find.by_ids.FindUserCompletionsByChallengesIdsResponse;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.apichallenge.shared.application.challenge.find.all.FindChallengesPaginatedResponse;
import org.rbernalop.apichallenge.shared.application.challenge.find.by_id.FindChallengeByIdResponse;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.valueobject.PaginationRequest;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class ChallengeFinder extends UseCase {
    private final ChallengeRepository challengeRepository;

    public ChallengeFinder(QueryBus queryBus, EventBus eventBus, ChallengeRepository challengeRepository) {
        super(queryBus, eventBus);
        this.challengeRepository = challengeRepository;
    }

    public FindChallengesPaginatedResponse findChallengesPaginated(PaginationRequest paginationRequest,
           UserUsername requesterUsername) {

        int page = paginationRequest.page();
        int size = paginationRequest.size();
        List<Challenge> challenges = challengeRepository.findAll(PageRequest.of(page, size)).getContent();

        List<String> challengeIds = challenges.stream().map(challenge -> challenge.getId().getValue()).toList();
        FindUserCompletionsByChallengesIdsQuery query =
            new FindUserCompletionsByChallengesIdsQuery(challengeIds, requesterUsername.getValue());
        FindUserCompletionsByChallengesIdsResponse userCompletions = ask(query);

        return FindChallengesPaginatedResponse.from(challenges, userCompletions.getCompletions(), challengeRepository.count());
    }

    public FindChallengeByIdResponse findById(ChallengeId challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() ->
            new EntityNotFoundException("Challenge not found")
        );
        return FindChallengeByIdResponse.from(challenge);
    }
}
