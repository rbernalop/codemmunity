package org.rbernalop.apichallenge.baseCode.domain.port;

import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;
import org.rbernalop.apichallenge.baseCode.domain.value_object.BaseCodeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseCodeRepository extends JpaRepository<BaseCode, BaseCodeId> {
}
