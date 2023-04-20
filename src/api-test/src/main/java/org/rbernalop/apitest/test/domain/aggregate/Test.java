package org.rbernalop.apitest.test.domain.aggregate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apitest.test.domain.value_object.TestId;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.ScriptContent;
import org.rbernalop.shared.domain.valueobject.ScriptId;


@Entity
@Table(name = "tests")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Test extends AggregateRoot {
    @EmbeddedId
    private TestId id;

    @Embedded
    private ScriptId scriptId;

    @Embedded
    private ScriptContent testContent;

    public static Test create(TestId id, ScriptId scriptId, ScriptContent testContent) {
        Test test = new Test();
        test.id = id;
        test.scriptId = scriptId;
        test.testContent = testContent;
        return test;
    }

    @Override
    public TestId getId() {
        return id;
    }

    public String getScriptId() {
        return scriptId.getValue();
    }

    public String getTestContent() {
        return testContent.getValue();
    }
}
