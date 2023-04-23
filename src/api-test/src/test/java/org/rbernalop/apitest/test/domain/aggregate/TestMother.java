package org.rbernalop.apitest.test.domain.aggregate;

import org.rbernalop.apitest.test.domain.value_object.ScriptContentMother;
import org.rbernalop.apitest.test.domain.value_object.TestIdMother;
import org.rbernalop.apitest.test.domain.value_object.TestName;
import org.rbernalop.apitest.test.domain.value_object.TestNameMother;
import org.rbernalop.shared.domain.valueobject.ChallengeId;
import org.rbernalop.shared.domain.valueobject.LanguageName;
import org.rbernalop.shared.domain.valueobject.ScriptContent;

import java.util.List;

public class TestMother {
    public static Test fromChallengeIdAndLanguageName(String challengeId, String languageName) {
        return Test.create(TestIdMother.random(), TestNameMother.random(), new ChallengeId(challengeId),
                ScriptContentMother.random(), new LanguageName(languageName));
    }

    public static List<Test> createPythonTestsFromChallengeIdAndLanguageName(String challengeId, String languageName) {
        LanguageName languageNameVO = new LanguageName(languageName);
        return List.of(
                Test.create(TestIdMother.random(), new TestName("test_empty_array"), new ChallengeId(challengeId),
                    new ScriptContent("""
                            import unittest
                            from Main import bubble_sort
         
                            class TestBubbleSort(unittest.TestCase):
                                def test_empty_array(self):
                                    arr = []
                                    self.assertEqual(bubble_sort(arr), [])
                            
                            if __name__ == "__main__":
                                unittest.main()"""),
                        languageNameVO),
                Test.create(TestIdMother.random(), new TestName("test_sorted_array"), new ChallengeId(challengeId),
                        new ScriptContent("""
                                import unittest
                                from Main import bubble_sort

                                class TestBubbleSort(unittest.TestCase):
                                    def test_sorted_array(self):
                                        arr = [1, 2, 3, 4, 5]
                                        self.assertEqual(bubble_sort(arr), [1, 2, 3, 4, 5])

                                if __name__ == "__main__":
                                    unittest.main()"""),
                        languageNameVO),
                Test.create(TestIdMother.random(), TestNameMother.random(), new ChallengeId(challengeId),
                        new ScriptContent("""
                                import unittest
                                from Main import bubble_sort

                                class TestBubbleSort(unittest.TestCase):
                                    def test_single_element_array(self):
                                        arr = [42]
                                        self.assertEqual(bubble_sort(arr), [42])

                                if __name__ == "main":
                                    unittest.main()"""),
                        languageNameVO),
                Test.create(TestIdMother.random(), new TestName("test_mixed_array"), new ChallengeId(challengeId),
                        new ScriptContent("""
                                import unittest
                                from Main import bubble_sort

                                class TestBubbleSort(unittest.TestCase):
                                    def test_mixed_array(self):
                                        arr = [5, -2, 0, 3, -1, 7, 8]
                                        self.assertEqual(bubble_sort(arr), [-2, -1, 0, 3, 5, 7, 8])

                                if __name__ == "main":
                                    unittest.main()"""),
                        languageNameVO)

        );
    }

    public static List<Test> createJavaTestsFromChallengeIdAndLanguageName(String challengeId, String languageName) {
        LanguageName languageNameVO = new LanguageName(languageName);
        return List.of(
            Test.create(TestIdMother.random(), new TestName("test_empty_array"), new ChallengeId(challengeId),
                    new ScriptContent("""
                            import org.junit.Test;
                            import static org.junit.Assert.assertArrayEquals;

                            public class MainTest {
                                @Test
                                public void test_empty_array() {
                                    int[] arr = {};
                                    int[] expected = {};
                                    assertArrayEquals(Main.bubbleSort(arr), expected);
                                }
                            }
                            """),
                    languageNameVO),
            Test.create(TestIdMother.random(), new TestName("test_sorted_array"), new ChallengeId(challengeId),
                    new ScriptContent("""
                            import org.junit.Test;
                            import static org.junit.Assert.assertArrayEquals;

                            public class MainTest {
                                @Test
                                public void test_sorted_array() {
                                    int[] arr = {1, 2, 3, 4, 5};
                                    int[] expected = {1, 2, 3, 4, 5};
                                    assertArrayEquals(Main.bubbleSort(arr), expected);
                                }
                            }
                            """),
                    languageNameVO),
            Test.create(TestIdMother.random(), new TestName("test_single_element_array"), new ChallengeId(challengeId),
                    new ScriptContent("""
                            import org.junit.Test;
                            import static org.junit.Assert.assertArrayEquals;

                            public class MainTest {
                                @Test
                                public void test_single_element_array() {
                                    int[] arr = {42};
                                    int[] expected = {42};
                                    assertArrayEquals(Main.bubbleSort(arr), expected);
                                }
                            }
                            """),
                    languageNameVO),
            Test.create(TestIdMother.random(), new TestName("test_mixed_array"), new ChallengeId(challengeId),
                    new ScriptContent("""
                            import org.junit.Test;
                            import static org.junit.Assert.assertArrayEquals;

                            public class MainTest {
                                @Test
                                public void test_mixed_array() {
                                    int[] arr = {5, -2, 0, 3, -1, 7, 8};
                                    int[] expected = {-2, -1, 0, 3, 5, 7, 8};
                                    assertArrayEquals(Main.bubbleSort(arr), expected);
                                }
                            }
                            """),
                    languageNameVO)
            );
    }

    public static List<Test> createNodeJsTestsFromChallengeIdAndLanguageName(String challengeId, String languageName) {
        LanguageName languageNameVO = new LanguageName(languageName);
        return List.of(
            Test.create(TestIdMother.random(), new TestName("test_duplicated_elements"), new ChallengeId(challengeId),
                new ScriptContent("""
                        const bubbleSort = require("./Main");
                                                        
                        test("sorts an array with duplicate elements", () => {
                            const arr = [2, 3, 1, 2, 5, 3];
                            expect(bubbleSort(arr)).toEqual([1, 2, 2, 3, 3, 5]);
                        });
                        """),
                languageNameVO),
            Test.create(TestIdMother.random(), new TestName("test_negative_elements"), new ChallengeId(challengeId),
                new ScriptContent("""
                        const bubbleSort = require("./Main");
                                                    
                        test("sorts an array with negative elements", () => {
                            const arr = [-2, 3, -1, 2, -5, 3];
                            expect(bubbleSort(arr)).toEqual([-5, -2, -1, 2, 3, 3]);
                        });
                        """),
                languageNameVO),
            Test.create(TestIdMother.random(), new TestName("test_equal_elements"), new ChallengeId(challengeId),
                new ScriptContent("""
                        const bubbleSort = require("./Main");
                                                    
                        test("sorts an array with all equal elements", () => {
                            const arr = [4, 4, 4, 4, 4];
                            expect(bubbleSort(arr)).toEqual([4, 4, 4, 4, 4]);
                        });
                        """),
                languageNameVO),
            Test.create(TestIdMother.random(), new TestName("test_multiple_elements"), new ChallengeId(challengeId),
                new ScriptContent("""
                        const bubbleSort = require("./Main");
                                                    
                        test("sorts an array with multiple elements", () => {
                            const arr = [3, 0, -5, 10, 8, -1];
                            expect(bubbleSort(arr)).toEqual([-5, -1, 0, 3, 8, 10]);
                        });
                        """),
                languageNameVO)
        );
    }
}