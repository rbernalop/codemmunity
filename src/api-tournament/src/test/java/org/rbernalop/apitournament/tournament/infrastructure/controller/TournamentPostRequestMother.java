package org.rbernalop.apitournament.tournament.infrastructure.controller;

import com.github.javafaker.Faker;

import java.time.Instant;
import java.time.Period;
import java.util.Date;

public class TournamentPostRequestMother {
    public static TournamentPostRequest random() {
        TournamentPostRequest request = new TournamentPostRequest();

        request.setId(Faker.instance().internet().uuid());
        request.setName(Faker.instance().name().name());
        request.setDescription(Faker.instance().lorem().paragraph());
        request.setBeginningDate(Date.from(Instant.now().plus(Period.ofDays(1))));
        request.setSize(Faker.instance().number().numberBetween(2, 15));

        return request;
    }
}