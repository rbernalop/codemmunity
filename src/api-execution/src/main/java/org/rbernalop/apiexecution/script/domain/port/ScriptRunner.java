package org.rbernalop.apiexecution.script.domain.port;

import org.rbernalop.apiexecution.script.domain.value_object.RunResult;

public interface ScriptRunner {
    RunResult run(String script);
}
