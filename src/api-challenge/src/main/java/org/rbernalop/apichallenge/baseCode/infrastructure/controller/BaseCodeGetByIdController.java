package org.rbernalop.apichallenge.baseCode.infrastructure.controller;

import lombok.*;
import org.rbernalop.apichallenge.shared.application.baseCode.find.FindBaseCodeByIdQuery;
import org.rbernalop.apichallenge.shared.application.baseCode.find.FindBaseCodeByIdResponse;
import org.rbernalop.shared.domain.bus.command.CommandBus;
import org.rbernalop.shared.domain.bus.query.QueryBus;
import org.rbernalop.shared.infrastructure.controller.ApiController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class BaseCodeGetByIdController extends ApiController {
    public BaseCodeGetByIdController(QueryBus queryBus, CommandBus commandBus) {
        super(queryBus, commandBus);
    }

    @GetMapping("baseCode")
    public BaseCodeGetByIdResponse handle(@RequestParam("languageName") String languageName, @RequestParam("challengeId") String challengeId) {
        FindBaseCodeByIdQuery query = new FindBaseCodeByIdQuery(challengeId, languageName);
        FindBaseCodeByIdResponse response = ask(query);
        return BaseCodeGetByIdResponse.from(response);
    }
}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
class BaseCodeGetByIdResponse {
    private String challengeId;
    private String languageName;
    private String code;

    public static BaseCodeGetByIdResponse from(FindBaseCodeByIdResponse response) {
        BaseCodeGetByIdResponse baseCodeGetByIdResponse = new BaseCodeGetByIdResponse();
        baseCodeGetByIdResponse.setChallengeId(response.getChallengeId());
        baseCodeGetByIdResponse.setLanguageName(response.getLanguageName());
        baseCodeGetByIdResponse.setCode(response.getCode());
        return baseCodeGetByIdResponse;
    }
}

