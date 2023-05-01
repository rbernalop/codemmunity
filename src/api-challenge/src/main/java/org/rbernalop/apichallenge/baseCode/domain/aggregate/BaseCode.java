package org.rbernalop.apichallenge.baseCode.domain.aggregate;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apichallenge.baseCode.domain.value_object.BaseCodeId;
import org.rbernalop.shared.domain.AggregateRoot;
import org.rbernalop.shared.domain.valueobject.Identifier;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

@Entity
@Table(name = "base_code")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = false)
public class BaseCode extends AggregateRoot {
    @EmbeddedId
    private BaseCodeId id;

    @Embedded
    private ScriptContent content;

    public BaseCode(BaseCodeId id, ScriptContent content) {
        this.id = id;
        this.content = content;
    }

    @Override
    public Identifier getId() {
        return null;
    }

    public BaseCodeId getBaseCodeId() {
        return id;
    }

    public String getChallengeId() {
        return id.getChallengeId();
    }

    public String getLanguageName() {
        return id.getLanguageName();
    }

    public String getContent() {
        return content.getValue();
    }
}
