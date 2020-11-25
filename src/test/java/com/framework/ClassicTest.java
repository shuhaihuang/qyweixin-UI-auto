package com.framework;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.stream.Stream;

/**
 * @Author sean
 * @Date 2020/11/25 22:15
 * @Version 1.0
 */
public class ClassicTest {
    static WebDriver driver;

    @ParameterizedTest
    @MethodSource()
    void search(String keyword){
        driver = new ChromeDriver();
        driver.get("https://ceshiren.com/");
        driver.findElement(By.id("search-button")).click();
        driver.findElement(By.id("search-term")).sendKeys(keyword);

    }
    static Stream<String>search(){
        return Stream.of("tian","didwang");
    }

}
