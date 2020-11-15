package com.work4weixin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * @Author sean
 * @Date 2020/11/15 11:49
 * @Version 1.0
 */
public class ContactPage extends BasePage{

    public ContactPage(WebDriver driver) {
        super(driver);
    }
    public ContactPage addMember(String username, String acctid, String mobile){

        return this;
    }

    public ContactPage searchDepartment(String departmentname){
        sendkeys(By.id("memberSearchInput"),departmentname);
        click(By.cssSelector(".ww_icon_AddMember"));

        return this;
    }

    public String getPartyInfo(){
        String text = driver.findElement(By.cssSelector(".js_party_info")).getText();
        System.out.println(text);
        click(By.cssSelector(".js_party_info"));
        return text;
    }

}
