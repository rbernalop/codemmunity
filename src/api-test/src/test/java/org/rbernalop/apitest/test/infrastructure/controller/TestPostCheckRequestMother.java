package org.rbernalop.apitest.test.infrastructure.controller;

import com.github.javafaker.Faker;

public class TestPostCheckRequestMother {
    public static TestPostCheckRequest createPythonRequest() {
        return new TestPostCheckRequest(Faker.instance().internet().uuid(), "def bubble_sort(arr):\n" +
                "    n = len(arr)\n" +
                "    for i in range(n):\n" +
                "        # Last i elements are already in place\n" +
                "        for j in range(0, n-i-1):\n" +
                "            # Traverse the array from 0 to n-i-1\n" +
                "            # Swap if the element found is greater than the next element\n" +
                "            if arr[j] > arr[j+1] :\n" +
                "                arr[j], arr[j+1] = arr[j+1], arr[j]\n" +
                "    return arr\n", "Python");
    }

    public static TestPostCheckRequest createJavaRequest() {
        return new TestPostCheckRequest(Faker.instance().internet().uuid(), "public class Main {\n" +
                "    public static int[] bubbleSort(int[] arr) {\n" +
                "        int n = arr.length;\n" +
                "        for (int i = 0; i < n - 1; i++) {\n" +
                "            for (int j = 0; j < n - i - 1; j++) {\n" +
                "                if (arr[j] > arr[j + 1]) {\n" +
                "                    int temp = arr[j];\n" +
                "                    arr[j] = arr[j + 1];\n" +
                "                    arr[j + 1] = temp;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        return arr;\n" +
                "    }\n" +
                "}", "Java");
    }

    public static TestPostCheckRequest createNodeJsRequest() {
        return new TestPostCheckRequest(Faker.instance().internet().uuid(),
                "    function bubbleSort(arr) {\n" +
                "        let n = arr.length;\n" +
                "        for (let i = 0; i < n - 1; i++) {\n" +
                "            for (let j = 0; j < n - i - 1; j++) {\n" +
                "                if (arr[j] > arr[j + 1]) {\n" +
                "                    let temp = arr[j];\n" +
                "                    arr[j] = arr[j + 1];\n" +
                "                    arr[j + 1] = temp;\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "        return arr;\n" +
                "    }\n" +
                "    module.exports = bubbleSort;\n", "NodeJS");

    }
}
