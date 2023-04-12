package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScriptsGetByUsernameRequest {
    private int page;
    private int size;
    private String username;
}
