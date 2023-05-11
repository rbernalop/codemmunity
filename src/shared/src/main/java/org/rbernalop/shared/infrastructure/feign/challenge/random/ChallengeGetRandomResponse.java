package org.rbernalop.shared.infrastructure.feign.challenge.random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ChallengeGetRandomResponse {
    private String id;
    private String title;
    private String description;
    private String category;
    private String userUsername;
    private long difficulty;
}
