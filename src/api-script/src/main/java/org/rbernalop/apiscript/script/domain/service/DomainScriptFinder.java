package org.rbernalop.apiscript.script.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.valueobject.PaginationRequest;
import org.rbernalop.apiscript.script.domain.valueobject.UserId;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@AllArgsConstructor
public class DomainScriptFinder {
    private final ScriptRepository scriptRepository;

    public List<Script> findScriptsByUserId(UserId userId, PaginationRequest paginationRequest) {
        return scriptRepository.findByOwnerId(
            userId,
            PageRequest.of(paginationRequest.page(), paginationRequest.size())
        );
    }
}
