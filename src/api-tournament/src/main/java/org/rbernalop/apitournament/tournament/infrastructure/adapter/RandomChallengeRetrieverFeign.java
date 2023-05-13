package org.rbernalop.apitournament.tournament.infrastructure.adapter;

import lombok.AllArgsConstructor;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallenge;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallengeBaseCode;
import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallengeCategory;
import org.rbernalop.apitournament.tournament.domain.port.RandomChallengeRetriever;
import org.rbernalop.apitournament.tournament.domain.value_object.TournamentChallengeBaseCodeId;
import org.rbernalop.shared.domain.valueobject.*;
import org.rbernalop.shared.infrastructure.feign.challenge.random.ChallengeGetRandomFeign;
import org.rbernalop.shared.infrastructure.feign.challenge.random.ChallengeGetRandomResponse;
import org.rbernalop.shared.infrastructure.feign.challenge.random.ChallengeGetRandomResponses;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RandomChallengeRetrieverFeign implements RandomChallengeRetriever {
    private final ChallengeGetRandomFeign challengeGetRandomFeign;

    private TournamentChallenge createChallenge(ChallengeGetRandomResponse challenge) {
        ChallengeId challengeId = new ChallengeId(challenge.getId());
        ChallengeTitle challengeTitle = new ChallengeTitle(challenge.getTitle());
        ChallengeDescription challengeDescription = new ChallengeDescription(challenge.getDescription());
        UserUsername userUsername = new UserUsername(challenge.getUserUsername());
        ChallengeDifficulty challengeDifficulty = new ChallengeDifficulty(challenge.getDifficulty());
        CategoryId categoryId = new CategoryId(challenge.getCategory());
        CategoryName categoryName = new CategoryName(challenge.getCategory());
        TournamentChallengeCategory challengeCategory = TournamentChallengeCategory.create(categoryId, categoryName);

        TournamentChallenge tournamentChallenge = TournamentChallenge.create(
            challengeId, challengeTitle, challengeDescription, userUsername, challengeDifficulty, challengeCategory
        );

        List<TournamentChallengeBaseCode> baseCodes = challenge.getBaseCodes().stream()
            .map(baseCode -> TournamentChallengeBaseCode.create(
                new TournamentChallengeBaseCodeId(tournamentChallenge, new LanguageName(baseCode.getLanguageName())),
                new ScriptContent(baseCode.getContent())
            )).toList();

        tournamentChallenge.setBaseCodes(baseCodes);
        return tournamentChallenge;
    }

    @Override
    public List<TournamentChallenge> getRandomChallenges(int numberOfChallenges) {
        ChallengeGetRandomResponses responses = challengeGetRandomFeign.getRandomChallenges(numberOfChallenges);
        return responses.getChallenges().stream().map(this::createChallenge).toList();
    }
}
