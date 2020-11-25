package com.framework;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * @Author sean
 * @Date 2020/11/25 22:55
 * @Version 1.0
 */
public class ParamsTest {
//    static WebDriver driver;

    @ParameterizedTest
    @MethodSource()
    void search(TestCase testCase){
/*        driver = new ChromeDriver();
        driver.get("https://ceshiren.com/");
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).sendKeys(keyword);*/
        System.out.println(testCase);
        testCase.run();

    }
/*    static List<String> search() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference = new TypeReference<List<String>>(){
        };
        List <String> kewords = mapper.readValue (
                ParamsTest.class.getResourceAsStream("/framework/search.yaml"),
                typeReference);
        return kewords;
    }*/

    static Stream<TestCase> search() throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestCase testCase = mapper.readValue (
                ParamsTest.class.getResourceAsStream("/framework/search.yaml"),
                TestCase.class);
        return Stream.of(testCase);
    }

}
