package org.rbernalop.shared.infrastructure.testing;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;

@ExtendWith(MockitoExtension.class)
public abstract class UnitTestCase {
    @Mock
    protected QueryBus queryBus;

    @Mock
    protected EventBus eventBus;
}
