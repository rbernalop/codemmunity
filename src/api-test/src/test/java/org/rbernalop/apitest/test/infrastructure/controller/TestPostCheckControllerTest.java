package org.rbernalop.apitest.test.infrastructure.controller;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.rbernalop.apitest.test.domain.aggregate.Test;
import org.rbernalop.apitest.test.domain.aggregate.TestMother;
import org.rbernalop.apitest.test.domain.port.TestRepository;
import org.rbernalop.shared.domain.bus.event.EventBus;
import org.rbernalop.shared.infrastructure.testing.IntegrationTestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TestPostCheckControllerTest extends IntegrationTestCase {
    private static final String TEST_CHECK_PATH = "/api/v1/test/check?user=";

    @Autowired
    private TestRepository testRepository;

    @MockBean
    private EventBus eventBus;

    private String username;

    @BeforeEach
    void setUp() {
        username = Faker.instance().name().username();
    }

    @AfterEach
    void tearDown() {
        testRepository.deleteAll();
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void should_return_ok_tests_results_and_send_them_to_queue(TestPostCheckRequest request, List<Test> tests) throws Exception {
        // GIVEN
        testRepository.saveAll(tests);

        // WHEN
        MvcResult mvcResult = assertRequestWithBody(HttpMethod.POST, TEST_CHECK_PATH + username, request, HttpStatus.OK);

        // THEN
        verify(eventBus, times(1)).publish(any());
        TestPostCheckResponse response =
            objectMapper.readValue(mvcResult.getResponse().getContentAsString(), TestPostCheckResponse.class);
        assertEquals(tests.size(), response.getResults().size());
        List<TestPostCheckResult> testResults = response.getResults();
        assertTrue(testResults.stream().allMatch(TestPostCheckResult::getPassed));
    }

    private static Object[][] testCases() {
        TestPostCheckRequest pythonRequest = TestPostCheckRequestMother.createPythonRequest();
        TestPostCheckRequest javaRequest = TestPostCheckRequestMother.createJavaRequest();
        TestPostCheckRequest nodeJsRequest = TestPostCheckRequestMother.createNodeJsRequest();
        return new Object[][]{
            {pythonRequest, TestMother.createPythonTestsFromChallengeIdAndLanguageName(pythonRequest.getChallengeId(), pythonRequest.getLanguageName())},
            {javaRequest, TestMother.createJavaTestsFromChallengeIdAndLanguageName(javaRequest.getChallengeId(), javaRequest.getLanguageName())},
            {nodeJsRequest, TestMother.createNodeJsTestsFromChallengeIdAndLanguageName(nodeJsRequest.getChallengeId(), nodeJsRequest.getLanguageName())}
        };
    }
}