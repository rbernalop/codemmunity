package org.rbernalop.apiscript.script.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.valueobject.UserId;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@AllArgsConstructor
public class DomainScriptFinder {
    private final ScriptRepository scriptRepository;

    public List<Script> findScriptsByUserId(UserId userId) {
        return scriptRepository.findByOwnerId(userId, PageRequest.of(0, 10));
    }
}
