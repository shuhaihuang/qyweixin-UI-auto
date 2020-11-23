package com.classic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * @Author sean
 * @Date 2020/11/13 0:31
 * @Version 1.0
 */
public class InteractiveTest {
    public static WebDriver driver;
    public static Actions actions;
//    public static WebDriverWait wait;
    @BeforeAll
    public static void initData(){
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
        actions = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    }
    @Test
    public void clickTest() {

        try {
            driver.get("https://www.baidu.com/");

            actions.doubleClick(driver.findElement(By.cssSelector("#hotsearch-refresh-btn")));

            actions.click(driver.findElement(By.cssSelector("#hotsearch-refresh-btn")));

            actions.contextClick(driver.findElement(By.cssSelector("#hotsearch-refresh-btn")));
            actions.perform();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void moveTest(){

        try {
            driver.get("https://www.baidu.com/");
            actions.moveToElement(driver.findElement(By.id("s-usersetting-top"))).perform();
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void dragTest(){

        try {
            driver.get("http://sahitest.com/demo/dragDropMooTools.htm");
            actions.dragAndDrop(driver.findElement(By.id("dragger")),driver.findElement(By.xpath("*//div[last()]")));
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void keyBoardTest(){
        driver.get("http://sahitest.com/demo/label.htm");
        driver.findElements(By.xpath("//input[@type='textbox']")).get(0).sendKeys("qingqing");
        actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).perform();
        actions.keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).perform();
        actions.keyDown(driver.findElements(By.xpath("//input[@type='textbox']")).get(1),Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();
    }

    @Test
    public void scrollTest() throws InterruptedException {
        driver.get("https://www.baidu.com/");
        driver.findElement(By.id("kw")).sendKeys("金刚川");

//        TouchActions actions = new TouchActions(driver);
        driver.findElement(By.xpath("//*[@id='su']")).click();

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//div[@class='page-inner']/a[last()]")).click();
        Thread.sleep(4000);

    }
/*    @AfterAll
    public static void tearDown(){
        driver.quit();
    }*/
}
