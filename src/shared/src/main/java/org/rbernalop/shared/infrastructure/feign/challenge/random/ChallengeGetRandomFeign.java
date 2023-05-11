package org.rbernalop.shared.infrastructure.feign.challenge.random;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "challenge", url = "API-CHALLENGE/api/v1")
public interface ChallengeGetRandomFeign {
    @GetMapping("challenge/random")
    ChallengeGetRandomResponses getRandomChallenges(@RequestParam("numChallenges") int numChallenges);
}
