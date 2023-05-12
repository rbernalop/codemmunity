package org.rbernalop.shared.infrastructure.feign.challenge.random;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class BaseCodeGetRandomResponse {
    private String languageName;
    private String content;
}
