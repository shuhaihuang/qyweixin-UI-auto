package com.classic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.BeforeAll;
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

import static org.junit.jupiter.api.Assertions.*;


/**
 * @Author sean
 * @Date 2020/11/11 22:53
 * @Version 1.0
 */
public class WebWorkWeixin {
    private static WebDriver driver;
    static void needLogin()  {
        //第一次先扫码登录

        try {
            WebDriver driver = new ChromeDriver();
            driver.get("https://work.weixin.qq.com/wework_admin/frame");
            Thread.sleep(15000);
            Set< Cookie > cookies = driver.manage().getCookies();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            mapper.writeValue(new File("cookies.yaml"),cookies);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("登录超时！");
        }

    }

    @BeforeAll
    static void beforeAll() throws InterruptedException, IOException {
        File file = new File("cookies.yaml");

        if (file.exists()){
            //如果登录成功过，就复用文件内的session进行登录
            driver = new ChromeDriver();
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
        }else {
            needLogin();
        }

    }

    @Test
    void contactAdd() throws InterruptedException {
//        click(By.linkText("通讯录"));
        click(By.linkText("添加成员"));
        sendkeys(By.name("username"),"lishi");
        sendkeys(By.name("acctid"),"1");
        sendkeys(By.name("mobile"),"12345678902");
        click(By.linkText("保存"));
    }

    @Test
    void searchDepartment(){
        click(By.id("menu_contacts"));
        sendkeys(By.id("memberSearchInput"),"IT运维部");
        click(By.cssSelector(".ww_icon_AddMember"));
        String text = driver.findElement(By.cssSelector(".js_party_info")).getText();
        assertTrue(text.contains("当前部门无任何成员"));
    }

    @Test
    void addDepartment() throws InterruptedException {
        click(By.id("menu_contacts"));
        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendkeys(By.name("name"),"俄罗斯基地");
        click(By.linkText("选择所属部门"));
        Thread.sleep(3000);
        click(By.xpath("(//ul[@role='group'])[3]/li"));
        click(By.linkText("确定"));

    }
    void click(By by){
        driver.findElement(by).click();
    }
    void sendkeys(By by,String contents){
        driver.findElement(by).sendKeys(contents);
    }



}
