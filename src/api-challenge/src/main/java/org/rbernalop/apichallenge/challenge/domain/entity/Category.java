package org.rbernalop.apichallenge.challenge.domain.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.rbernalop.apichallenge.challenge.domain.value_object.CategoryId;
import org.rbernalop.apichallenge.challenge.domain.value_object.CategoryName;
import org.rbernalop.shared.domain.AggregateRoot;

@Entity
@Table(name = "categories")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Category extends AggregateRoot {
    @EmbeddedId
    private CategoryId id;

    @Embedded
    private CategoryName name;

    public static Category create(CategoryId id, CategoryName name) {
        Category category = new Category();
        category.id = id;
        category.name = name;
        return category;
    }

    public CategoryId getId() {
        return id;
    }

    public String getName() {
        return name.getValue();
    }
}
