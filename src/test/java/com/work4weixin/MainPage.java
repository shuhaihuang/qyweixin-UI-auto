package com.work4weixin;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author sean
 * @Date 2020/11/15 7:58
 * @Version 1.0
 */
public class MainPage extends BasePage {

    void needLogin() throws IOException, InterruptedException {

        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        Thread.sleep(20000);
        Set< Cookie > cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);
        //第一次登录获取cookie存于本地，需要扫一扫
    }

    void beforeAll() throws IOException, InterruptedException {

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference =new TypeReference<List<HashMap<String,Object>>>() {};
        List<HashMap<String,Object>> cookies = mapper.readValue(new File("cookies.yaml"),typeReference);

        cookies.forEach(cookieMap->{
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
        });

        driver.navigate().refresh();
        driver.manage().window().maximize();
        Thread.sleep(3000);
    }


    public MainPage() throws IOException, InterruptedException {

        this.beforeAll();

    }

    public ContactPage contact() {

        click(By.id("menu_contacts"));
        return new ContactPage(driver);
    }


}
