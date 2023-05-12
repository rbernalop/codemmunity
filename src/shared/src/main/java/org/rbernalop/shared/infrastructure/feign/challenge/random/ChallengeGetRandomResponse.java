package org.rbernalop.shared.infrastructure.feign.challenge.random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class ChallengeGetRandomResponse {
    private String id;
    private String title;
    private String description;
    private String category;
    private String userUsername;
    private long difficulty;
    private List<BaseCodeGetRandomResponse> baseCodes;
}
