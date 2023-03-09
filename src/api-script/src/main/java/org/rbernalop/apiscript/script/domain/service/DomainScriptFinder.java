package org.rbernalop.apiscript.script.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.valueobject.PaginationRequest;
import org.rbernalop.apiscript.script.domain.valueobject.OwnerUsername;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@AllArgsConstructor
public class DomainScriptFinder {
    private final ScriptRepository scriptRepository;

    public List<Script> findScriptsByOwnerUsername(OwnerUsername ownerUsername, PaginationRequest paginationRequest) {
        return scriptRepository.findByOwnerUsername(
                ownerUsername,
            PageRequest.of(paginationRequest.page(), paginationRequest.size())
        );
    }
}
