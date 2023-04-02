package org.rbernalop.apiexecution.language.infrastructure.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.rbernalop.apiexecution.shared.application.language.find.LanguageResponse;
import org.rbernalop.apiexecution.shared.application.language.find.all.FindAllLanguagesQuery;
import org.rbernalop.apiexecution.shared.application.language.find.all.FindAllLanguagesResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class LanguageGetController extends ApiController {

    public LanguageGetController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("language")
    public LanguageGetControllerResponse handle() {
        FindAllLanguagesQuery query = new FindAllLanguagesQuery();
        FindAllLanguagesResponse response = ask(query);
        return new LanguageGetControllerResponse(response.getLanguages());
    }
}

@NoArgsConstructor
@Getter
class LanguageGetControllerResponse {
    List<LanguageGetResponse> languages;

    public LanguageGetControllerResponse(List<LanguageResponse> languages) {
        this.languages = languages.stream().map(LanguageGetResponse::new).toList();
    }
}

@NoArgsConstructor
@Getter
class LanguageGetResponse {
    private String id;
    private String name;

    public LanguageGetResponse(LanguageResponse language) {
        this.id = language.getId();
        this.name = language.getName();
    }
}