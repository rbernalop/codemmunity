package org.rbernalop.servicesynchronization.script.domain.port;

import org.rbernalop.servicesynchronization.script.domain.aggregate.Script;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScriptRepository extends JpaRepository<Script, ScriptId> {
}
