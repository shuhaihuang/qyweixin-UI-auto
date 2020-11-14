package com.workweixin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
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
 * @Date 2020/11/11 22:53
 * @Version 1.0
 */
public class WebWorkWeixin {
    @Test
    void login() throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        Thread.sleep(20000);
        Set< Cookie > cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies);
        //第一次登录获取cookie存于本地，需要扫一扫
    }

    @Test
    void logined() throws InterruptedException, IOException {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get("https://work.weixin.qq.com/wework_admin/frame");

        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference typeReference =new TypeReference<List<HashMap<String,Object>>>() {};
        List<HashMap<String,Object>> cookies = mapper.readValue(new File("cookies.yaml"),typeReference);
//        System.out.println(cookies);

        cookies.forEach(cookieMap->{
            driver.manage().addCookie(new Cookie(cookieMap.get("name").toString(),cookieMap.get("value").toString()));
        });

        driver.navigate().refresh();
        driver.manage().window().maximize();

        driver.findElement(By.id("menu_index")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"_hmt_click\"]/div[1]/div[4]/div[2]/a[1]/div/span[2]")).click();
        Thread.sleep(6000);

        //输入信息
        driver.findElement(By.id("username")).sendKeys("张三");
        Thread.sleep(1000);
        driver.findElement(By.name("acctid")).sendKeys("2226357440");
        Thread.sleep(1000);
        driver.findElement(By.id("memberAdd_phone")).sendKeys("13428283251");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"js_contacts64\"]/div/div[2]/div/div[4]/div/form/div[1]/a[1]")).click();
        Thread.sleep(5000);

        //退出浏览器
        driver.quit();
    }

}
