package com.work4weixin.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Author sean
 * @Date 2020/11/15 7:57
 * @Version 1.0
 */
public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
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
