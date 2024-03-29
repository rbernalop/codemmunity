package org.rbernalop.apichallenge.shared.application.challenge.find.all;

import com.github.javafaker.Faker;

public class FindChallengesPaginatedQueryMother {

    public static FindChallengesPaginatedQuery random() {
        FindChallengesPaginatedQuery findChallengesPaginatedQuery = new FindChallengesPaginatedQuery();
        findChallengesPaginatedQuery.setPage(0);
        findChallengesPaginatedQuery.setSize(Faker.instance().number().numberBetween(1, 10));
        findChallengesPaginatedQuery.setRequesterUsername(Faker.instance().name().username());
        return findChallengesPaginatedQuery;
    }
}