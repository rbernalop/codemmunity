package org.rbernalop.apiscript.script.domain.valueobject;

public record PaginationRequest(int page, int size) {
    public PaginationRequest {
        if (page < 0) {
            throw new IllegalArgumentException("Page must be greater than 0");
        }
        if (size <= 0) {
            throw new IllegalArgumentException("Size must be greater than 0");
        }
    }
}
