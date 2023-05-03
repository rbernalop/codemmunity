package org.rbernalop.apichallenge.baseCode.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCode;
import org.rbernalop.apichallenge.baseCode.domain.aggregate.BaseCodeMother;
import org.rbernalop.apichallenge.baseCode.domain.port.BaseCodeRepository;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseCodeGetByIdResponseTest extends IntegrationTestCase {
    private static final String BASE_CODE_GET_BY_ID_PATH = "/api/v1/baseCode";

    @Autowired
    private BaseCodeRepository repository;

    private BaseCodeGetByIdResponse parseBaseCodeGetByIdResponse(String input) {
        Pattern pattern = Pattern.compile("BaseCodeGetByIdResponse\\(challengeId=(.+?), languageName=(.+?), code=(.*?)\\)");
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            String challengeId = matcher.group(1);
            String languageName = matcher.group(2);
            String code = matcher.group(3);

            return new BaseCodeGetByIdResponse(challengeId, languageName, code);
        }

        return null;
    }

    @Test
    void should_return_base_code_by_id() throws Exception {
        // GIVEN
        BaseCode baseCode = BaseCodeMother.random();
        repository.save(baseCode);

        // WHEN
        MvcResult mvcResult = assertRequest(
            HttpMethod.GET,
            BASE_CODE_GET_BY_ID_PATH + "?languageName=" + baseCode.getLanguageName() + "&challengeId=" + baseCode.getChallengeId(),
            HttpStatus.OK
        );

        // THEN
        BaseCodeGetByIdResponse response = parseBaseCodeGetByIdResponse(mvcResult.getResponse().getContentAsString());
        assertNotNull(response);
        assertEquals(baseCode.getChallengeId(), response.getChallengeId());
        assertEquals(baseCode.getLanguageName(), response.getLanguageName());
        assertEquals(baseCode.getContent(), response.getCode());
    }
}