package com.aice;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * @Author sean
 * @Date 2020/11/13 0:31
 * @Version 1.0
 */
public class WindowTest {
    public static WebDriver driver;
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @Test
    public void switchWindowTest() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath("//*[@id='u1']/a[last()]")).click();
//        Thread.sleep(2000);
        String currentwin = driver.getWindowHandle();
        driver.findElement(By.linkText("立即注册")).click();
        for (String win:driver.getWindowHandles()
             ) {
            if(!win.equals(currentwin)){
                driver.switchTo().window(win);
                driver.findElement(By.name("userName")).sendKeys("shuhai");
                driver.findElement(By.name("phone")).sendKeys("12345678901");

                driver.switchTo().window(currentwin);
                driver.findElement(By.xpath("//p[@title=\"用户名登录\"]")).click();
                driver.findElement(By.name("userName")).sendKeys("shuhai");
                driver.findElement(By.name("password")).sendKeys("12345678901");
                driver.findElements(By.xpath("//input[@type=\"submit\"]")).get(1).click();
            }
        }

    }
}