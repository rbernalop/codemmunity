package org.rbernalop.apichallenge.shared.application.baseCode.find;

import com.github.javafaker.Faker;

public class FindBaseCodeByIdResponseMother {
    public static FindBaseCodeByIdResponse fromChallengeId(String challengeId) {
        return new FindBaseCodeByIdResponse(challengeId, Faker.instance().name().name(), Faker.instance().lorem().paragraph());
    }
}