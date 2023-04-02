package org.rbernalop.apiscript.script.application.modify;

import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.service.DomainScriptFinder;
import org.rbernalop.apiscript.script.domain.value_object.*;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.domain.exception.NotAllowedOperationException;
import org.rbernalop.shared.domain.valueobject.LanguageId;

import java.util.Objects;

public class ScriptModifier extends UseCase {

    private final ScriptRepository scriptRepository;

    private final DomainScriptFinder domainScriptFinder;

    public ScriptModifier(QueryBus queryBus, ScriptRepository scriptRepository) {
        super(queryBus);
        this.scriptRepository = scriptRepository;
        this.domainScriptFinder = new DomainScriptFinder(scriptRepository);
    }

    public void renameScript(ScriptId scriptId, ScriptName nextName, OwnerUsername ownerUsername) {
        Script script = domainScriptFinder.findScriptById(scriptId);

        if(!Objects.equals(script.getOwnerName(), ownerUsername.getValue())) {
            throw new NotAllowedOperationException("You can't rename another user's script");
        }

        script.rename(nextName);
        scriptRepository.save(script);
    }

    public void regenerateShareKey(ScriptId id, ShareKey shareKey) {
        Script script = domainScriptFinder.findScriptById(id);
        script.renewShareKey(shareKey);
        scriptRepository.save(script);
    }

    public void changeScriptLanguage(ScriptId id, LanguageId languageId) {
        Script script = domainScriptFinder.findScriptById(id);
        if(!script.alreadyHasLanguage(languageId)) {
            script.changeLanguage(languageId);
            scriptRepository.save(script);
        }
    }
}
