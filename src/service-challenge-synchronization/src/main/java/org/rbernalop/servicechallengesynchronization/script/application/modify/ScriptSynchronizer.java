package org.rbernalop.servicechallengesynchronization.script.application.modify;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.rbernalop.servicechallengesynchronization.script.domain.aggregate.ChallengeScript;
import org.rbernalop.servicechallengesynchronization.script.domain.port.ChallengeScriptRepository;
import org.rbernalop.servicechallengesynchronization.script.domain.value_object.ChallengeScriptId;
import org.rbernalop.servicechallengesynchronization.shared.domain.port.ChallengeScriptManager;
import org.rbernalop.shared.domain.Service;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class ScriptSynchronizer {
    private final ChallengeScriptManager challengeScriptManager;
    private final ChallengeScriptRepository challengeScriptRepository;

    @Scheduled(cron = "0 * * * * *")
    public void synchronizeScriptsContent() {
        log.info("Synchronizing scripts content with database");
        Map<ChallengeScriptId, ChallengeScript> scripts = challengeScriptManager.getScriptsContent();
        for (Map.Entry<ChallengeScriptId, ChallengeScript> entry : scripts.entrySet()) {
            ChallengeScriptId id = entry.getKey();
            ChallengeScript storedScript = entry.getValue();
            Optional<ChallengeScript> optionalScript = challengeScriptRepository.findById(id);
            if (optionalScript.isPresent()) {
                ChallengeScript script = optionalScript.get();
                script.update(storedScript);
                challengeScriptRepository.save(script);
            }
        }
    }

}
