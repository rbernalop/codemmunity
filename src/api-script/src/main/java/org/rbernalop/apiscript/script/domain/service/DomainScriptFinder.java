package org.rbernalop.apiscript.script.domain.service;

import lombok.AllArgsConstructor;
import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.port.ScriptRepository;
import org.rbernalop.shared.domain.valueobject.PaginationRequest;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.exception.EntityNotFoundException;
import org.rbernalop.shared.domain.valueobject.UserUsername;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@AllArgsConstructor
public class DomainScriptFinder {
    private final ScriptRepository scriptRepository;

    public List<Script> findScriptsByuserUsername(UserUsername userUsername, PaginationRequest paginationRequest) {
        return scriptRepository.findByuserUsername(
                userUsername,
            PageRequest.of(paginationRequest.page(), paginationRequest.size())
        );
    }

    public Script findScriptById(ScriptId id) {
        return scriptRepository.findById(id).orElseThrow(
            () -> new EntityNotFoundException("Script with id '" + id.getValue() + "' not found")
        );
    }
}
