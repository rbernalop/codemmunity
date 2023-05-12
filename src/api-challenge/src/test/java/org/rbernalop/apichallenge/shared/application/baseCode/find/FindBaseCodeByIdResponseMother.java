package org.rbernalop.apichallenge.shared.application.baseCode.find;

import com.github.javafaker.Faker;
import org.rbernalop.apichallenge.shared.application.baseCode.find.by_id.FindBaseCodeByIdResponse;

public class FindBaseCodeByIdResponseMother {
    public static FindBaseCodeByIdResponse fromChallengeId(String challengeId) {
        return new FindBaseCodeByIdResponse(challengeId, Faker.instance().name().name(), Faker.instance().lorem().paragraph());
    }
}