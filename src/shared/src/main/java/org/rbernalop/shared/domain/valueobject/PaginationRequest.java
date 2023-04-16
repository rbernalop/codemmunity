package org.rbernalop.shared.domain.valueobject;

import org.rbernalop.shared.domain.exception.NegativeException;

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
