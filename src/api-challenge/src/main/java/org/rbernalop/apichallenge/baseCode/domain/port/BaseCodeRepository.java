package org.rbernalop.apichallenge.baseCode.domain.port;

import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;
import org.rbernalop.apichallenge.baseCode.domain.value_object.BaseCodeId;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BaseCodeRepository extends JpaRepository<BaseCode, BaseCodeId> {
    List<BaseCode> findById_ChallengeIdIn(List<ChallengeId> challengeIds);
}
