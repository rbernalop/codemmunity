package org.rbernalop.shared.infrastructure;

import org.rbernalop.shared.domain.Service;
import org.rbernalop.shared.domain.UuidGenerator;
import java.util.UUID;

@Service
public final class JavaUuidGenerator implements UuidGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
