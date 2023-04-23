package org.rbernalop.apitest.test.domain.port;

import org.rbernalop.apitest.test.domain.aggregate.Test;
import org.rbernalop.apitest.test.domain.value_object.TestId;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<Test, TestId>  {
    List<Test> findByChallengeIdAndLanguageName(ChallengeId challengeId, LanguageName languageName);
}
