package org.rbernalop.apiscript.script.infrastructure.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ScriptsGetByUserIdRequest {
    private int page;
    private int size;
    private String ownerId;
}
