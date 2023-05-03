package org.rbernalop.apichallenge.challenge.domain.entity;

import org.rbernalop.apichallenge.challenge.domain.value_object.CategoryIdMother;
import org.rbernalop.apichallenge.challenge.domain.value_object.CategoryNameMother;

public class ChallengeCategoryMother {

    public static Category random() {
        return Category.create(
            CategoryIdMother.random(),
            CategoryNameMother.random()
        );
    }
}