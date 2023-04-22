package org.rbernalop.apitest.test.domain.aggregate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apitest.test.domain.value_object.TestId;
import org.rbernalop.apitest.test.domain.value_object.TestName;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;


@Entity
@Table(name = "tests")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class Test extends AggregateRoot {
    @EmbeddedId
    private TestId id;

    @Embedded
    private TestName testName;

    @Embedded
    private ChallengeId challengeId;

    @Embedded
    private ScriptContent testContent;

    @Embedded
    private LanguageName languageName;

    public static Test create(TestId id, TestName testName, ChallengeId challengeId, ScriptContent testContent,
          LanguageName languageName) {
        Test test = new Test();
        test.id = id;
        test.testName = testName;
        test.challengeId = challengeId;
        test.testContent = testContent;
        test.languageName = languageName;
        return test;
    }

    @Override
    public TestId getId() {
        return id;
    }

    public String getTestName() {
        return testName.getValue();
    }

    public String getChallengeId() {
        return challengeId.getValue();
    }

    public String getTestContent() {
        return testContent.getValue();
    }

    public String getLanguageName() {
        return languageName.getValue();
    }
}
