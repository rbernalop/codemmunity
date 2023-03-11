package org.rbernalop.apiscript.script.infrastructure.controller;

public class ScriptPatchRequestMother {
    public static ScriptPatchRequest fromName(String name) {
        return new ScriptPatchRequest(name);
    }
}
