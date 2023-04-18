package org.rbernalop.servicesynchronization.script.application.modify;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicesynchronization.script.domain.aggregate.Script;
import org.rbernalop.servicesynchronization.script.domain.port.ScriptRepository;
import org.rbernalop.servicesynchronization.shared.domain.port.ScriptManager;
import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.ScriptId;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ScriptSynchronizer {
    private final ScriptManager scriptManager;
    private final ScriptRepository scriptRepository;

    @Scheduled(cron = "0 * * * * *")
    public void synchronizeScriptsContent() {
        log.info("Synchronizing scripts content with database");
        Map<ScriptId, ScriptContent> scripts = scriptManager.getScriptsContent();
        for (Map.Entry<ScriptId, ScriptContent> entry : scripts.entrySet()) {
            ScriptId id = entry.getKey();
            ScriptContent content = entry.getValue();
            Optional<Script> optionalScript = scriptRepository.findById(id);
            if (optionalScript.isPresent()) {
                Script script = optionalScript.get();
                script.changeContent(content);
                scriptRepository.save(script);
            }
        }
    }

}
