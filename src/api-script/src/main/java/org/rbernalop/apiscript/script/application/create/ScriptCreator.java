package org.rbernalop.apiscript.script.application.create;

import org.rbernalop.apiscript.script.domain.aggregate.Script;
import org.rbernalop.apiscript.script.domain.repository.ScriptRepository;
import org.rbernalop.apiscript.script.domain.value_object.OwnerUsername;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.rbernalop.shared.domain.valueobject.LanguageId;
import org.rbernalop.apiscript.script.domain.value_object.ShareKey;
import org.rbernalop.shared.application.UseCase;
import org.rbernalop.shared.domain.bus.query.QueryBus;

public class ScriptCreator extends UseCase {

    private final ScriptRepository scriptRepository;

    public ScriptCreator(QueryBus queryBus, ScriptRepository scriptRepository) {
        super(queryBus);
        this.scriptRepository = scriptRepository;
    }

    public void create(ScriptId id, ShareKey key, LanguageId languageId, OwnerUsername ownerUserName) {
        Script script = Script.create(id, key, languageId, ownerUserName);
        scriptRepository.save(script);
    }

}
