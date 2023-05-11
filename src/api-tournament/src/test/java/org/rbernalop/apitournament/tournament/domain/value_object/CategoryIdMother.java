package org.rbernalop.apitournament.tournament.domain.value_object;

import com.github.javafaker.Faker;
import org.rbernalop.shared.domain.valueobject.CategoryId;

public class CategoryIdMother {
    public static CategoryId random() {
        return new CategoryId(Faker.instance().internet().uuid());
    }
}
