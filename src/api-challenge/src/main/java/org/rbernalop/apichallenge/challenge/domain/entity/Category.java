package org.rbernalop.apichallenge.challenge.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apichallenge.challenge.domain.value_object.CategoryId;
import org.rbernalop.apichallenge.challenge.domain.value_object.CategoryName;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Category {
    @EmbeddedId
    private CategoryId id;

    @Embedded
    private CategoryName name;

    public String getId() {
        return id.getValue();
    }

    public String getName() {
        return name.getValue();
    }
}
