package org.rbernalop.apiscript.script.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.valueobject.UserId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
public class DomainScriptFinder {
    private final ScriptRepository scriptRepository;

    public Page<Script> findScriptsByUserId(UserId userId) {
        return scriptRepository.findByOwnerId(userId, Pageable.ofSize(10).withPage(0));
    }
}
