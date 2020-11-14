package com.aice;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * @Author sean
 * @Date 2020/11/12 21:04
 * @Version 1.0
 */
public class AiceTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,5);
    }

    @Test
    public void login() throws InterruptedException {
        driver.get("https://ceshiren.com/");
//        Thread.sleep(3000);

        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
        driver.findElement(By.id("login-account-name")).clear();
        driver.findElement(By.id("login-account-name")).sendKeys("773025982");
        driver.findElement(By.id("login-account-password")).clear();
        driver.findElement(By.id("login-account-password")).sendKeys("Azsx123#!");
        driver.findElement(By.cssSelector("#login-button")).click();
        Thread.sleep(30000);
    }

    @Test
    public void timeSleepTest() throws InterruptedException {
        driver.get("https://ceshiren.com/");

        Thread.sleep(5000);

        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
    }

    @Test
    public void waitTest(){
        driver.get("https://ceshiren.com/");
//        driver.findElement(By.xpath("//span[contains(text(),'登录')]")).click();
//使用wait util
        WebElement loginEle = wait.until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver driver){
                return driver.findElement(By.xpath("//span[contains(text(),'登录')]"));
            }
        });
        loginEle.click();

/*        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'登录')]")));
        element.click();*/
        //第2种写法,执行过程多种判断
    }
    @AfterAll
    public static void tearDown(){
        driver.quit();
    }
}
