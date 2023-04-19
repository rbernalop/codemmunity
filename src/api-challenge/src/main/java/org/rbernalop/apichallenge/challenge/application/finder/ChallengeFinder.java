package org.rbernalop.apichallenge.challenge.application.finder;

import org.rbernalop.apichallenge.challenge.domain.aggregate.Challenge;
import org.rbernalop.apichallenge.challenge.domain.port.ChallengeRepository;
import org.rbernalop.apichallenge.challenge.domain.value_object.ChallengeId;
import org.rbernalop.apichallenge.shared.application.challenge.finder.all.FindChallengesPaginatedResponse;
import org.rbernalop.apichallenge.shared.application.challenge.finder.by_id.FindChallengeByIdResponse;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.valueobject.PaginationRequest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class ChallengeFinder extends UseCase {
    private final ChallengeRepository challengeRepository;

    public ChallengeFinder(QueryBus queryBus, EventBus eventBus, ChallengeRepository challengeRepository) {
        super(queryBus, eventBus);
        this.challengeRepository = challengeRepository;
    }

    public FindChallengesPaginatedResponse findChallengesPaginated(PaginationRequest paginationRequest) {

        List<Challenge> challenges = challengeRepository.findAll(
            PageRequest.of(paginationRequest.page(), paginationRequest.size())
        ).getContent();
        return FindChallengesPaginatedResponse.from(challenges, challengeRepository.count());
    }

    public FindChallengeByIdResponse findById(ChallengeId challengeId) {
        Challenge challenge = challengeRepository.findById(challengeId).orElseThrow(() ->
            new EntityNotFoundException("Challenge not found")
        );
        return FindChallengeByIdResponse.from(challenge);
    }
}
