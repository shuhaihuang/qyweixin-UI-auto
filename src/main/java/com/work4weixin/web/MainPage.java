package com.work4weixin.web;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

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
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
        options.addArguments("--user-data-dir='C:\\Users\\sean\\AppData\\Local\\Google\\Chrome\\User Data\\Default'");
        options.addArguments("-headless");
        driver = new ChromeDriver(options);

        driver.get("https://work.weixin.qq.com/wework_admin/frame");
        driver.manage().window().maximize();
        Thread.sleep(5000);
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        Set< Cookie > cookies = driver.manage().getCookies();
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.writeValue(new File("cookies.yaml"),cookies); //第一次登录获取cookie存于本地，需要扫一扫
    }

    void beforeAll() throws IOException, InterruptedException {
        File file = new File("cookies.yaml");

        if (file.exists()){
            //如果登录成功过，就复用yaml文件内的session进行登录
            ChromeOptions options = new ChromeOptions();
            System.setProperty("webdriver.chrome.driver", "D:\\chromedriver\\chromedriver.exe");
            options.addArguments("--user-data-dir='C:\\Users\\sean\\AppData\\Local\\Google\\Chrome\\User Data\\Default'");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);

            driver.get("https://work.weixin.qq.com/wework_admin/frame#index");
            Thread.sleep(5000);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            TypeReference typeReference =new TypeReference<List<HashMap<String,Object>>>() {};
            List<HashMap<String,Object>> cookies = mapper.readValue(new File("cookies.yaml"),typeReference);
            System.out.println(cookies);
            cookies.forEach(cookieMap->{
                driver.manage().addCookie(new Cookie(
                        cookieMap.get("name").toString(),
                        cookieMap.get("value").toString()
                ));
            });
            Thread.sleep(2000);
            driver.navigate().refresh();
            Thread.sleep(3000);
        }else {
            needLogin();
        }
    }

    public MainPage() throws IOException, InterruptedException {
        //初始化selenium，复用session，打开网站
        this.beforeAll();
    }

    public ContactPage contact() {
        //进入通信录
        click(By.id("menu_contacts"));
        //传递selenium的driver给另一个PO
        //po原则4 跳转或者进入新页面使用返回新的po来模拟
        return new ContactPage(driver);
    }
    //多浏览器兼容测试
            /*if(System.getenv("browser")=="chrome"){
                driver = new ChromeDriver();
            }else if(System.getenv("browser")=="firefox"){
                driver = new FirefoxDriver();
            }*/
}
