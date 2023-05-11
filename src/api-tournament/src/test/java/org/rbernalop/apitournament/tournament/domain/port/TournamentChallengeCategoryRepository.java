package org.rbernalop.apitournament.tournament.domain.port;

import org.rbernalop.apitournament.tournament.domain.entity.TournamentChallengeCategory;
import org.rbernalop.shared.domain.valueobject.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TournamentChallengeCategoryRepository extends JpaRepository<TournamentChallengeCategory, CategoryId> {
}
