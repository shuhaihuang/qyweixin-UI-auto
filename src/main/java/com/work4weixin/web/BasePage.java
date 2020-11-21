package com.work4weixin.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * @Author sean
 * @Date 2020/11/15 7:57
 * @Version 1.0
 */
public class BasePage {
    protected WebDriver driver;
    ChromeOptions options = new ChromeOptions();

    public BasePage(WebDriver driver) {
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
        options.addArguments("--user-data-dir='C:\\Users\\sean\\AppData\\Local\\Google\\Chrome\\User Data\\Default'");
        options.addArguments("-headless");
        this.driver=driver;
    }

    public BasePage() {

    }

    void click(By by){
        driver.findElement(by).click();
    }
    void sendkeys(By by,String contents){
        driver.findElement(by).sendKeys(contents);
    }

}
