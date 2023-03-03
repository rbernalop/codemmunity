package org.rbernalop.apiscript.script.domain.repository;

import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.valueobject.ScriptId;
import org.rbernalop.apiscript.script.domain.valueobject.UserId;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScriptRepository extends JpaRepository<Script, ScriptId> {
    List<Script> findByOwnerId(UserId ownerId, Pageable pageable);
}
