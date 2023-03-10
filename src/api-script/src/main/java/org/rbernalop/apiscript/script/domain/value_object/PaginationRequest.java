package org.rbernalop.apiscript.script.domain.value_object;

import org.rbernalop.apiscript.shared.domain.exception.NegativeException;

public record PaginationRequest(int page, int size) {
    public PaginationRequest {
        if (page < 0) {
            throw new NegativeException("Page must be greater than 0");
        }
        if (size < 0) {
            throw new NegativeException("Size must be greater than 0");
        }
    }
}
