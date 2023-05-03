
export const NODEJS_LANGUAGE =
{
        id: 'ccd651ad-012d-4c01-8287-43e117f75de6',
        name: "NodeJS",
        key: "javascript",
        icon: <i className="devicon-nodejs-plain" style={{fontSize: 22}}></i>,
        sample: `/* Simple Hello World in Node.js */
console.log("Hello World");
`
};
export const PYTHON_LANGUAGE =
{
        id: 'f7f620d1-cf38-431e-9640-9a6a05dea978',
        name: "Python",
        key: "python",
        icon: <i className="devicon-python-plain" style={{fontSize: 22}}></i>,
        sample: `# Simple Hello World in Python
print("Hello World")
`
};

export const JAVA_LANGUAGE =
{
        id: '35ecd470-d2eb-4e57-b9ae-970a8686b7bd',
        name: "Java",
        key: "text/x-java",
        icon: <i className="devicon-java-plain" style={{fontSize: 22}}></i>,
        sample: `// Simple Hello World in Java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
`
};

export const LANGUAJES = [
    NODEJS_LANGUAGE,
    PYTHON_LANGUAGE,
    JAVA_LANGUAGE
];