package com.work4weixin.web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


/**
 * @Author sean
 * @Date 2020/11/15 11:49
 * @Version 1.0
 */
public class ContactPage extends BasePage{
    //po原则2 不要暴露页面内部实现细节
    private By parterInfo = By.cssSelector(".js_party_info");
    private By party_name = By.cssSelector("#party_name");

    public ContactPage(WebDriver driver) {
        //保存driver到自己的实例
        super(driver);
    }
    //po原则6 添加成功的时候与添加失败返回的页面是不同的，需要封装为不同的方法
    public ContactPage addMember(String name,String accountid,String phoneNum) throws InterruptedException {
        Thread.sleep(5000);
        click(By.xpath("(//a[contains(text(),'添加成员')])[2]"));
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
        sendkeys(By.id("username"),name);
        sendkeys(By.id("memberAdd_acctid"),accountid);
        sendkeys(By.id("memberAdd_phone"),phoneNum);
        click(By.xpath("(//a[contains(text(),'保存')])[2]"));
        Thread.sleep(2000);
        String addedName = getMember(name);
        System.out.println("成员["+addedName+"]已添加！");
        click(By.id("menu_index")); //回到主页
        getMainPageMsg();
        return this;
    }

    public String getMainPageMsg() {
        String text = driver.findElement(By.cssSelector(".index_explore_title")).getText();
        return text;
    }

    private String getMember(String username) {
        String text = driver.findElement(By.xpath("(//a[contains(text(),'"+username+"')])[2]")).getText();
        return text;
    }

    //po原则6 添加失败返回的页面是不同的，需要封装为不同的方法
    public ContactPage addMemberFail(String username, String acctid, String mobile){
        return this;
    }
    //po原则5 不用实现所有的方法，按需封装
    public ContactPage searchDepartment(String departmentname){
        //po原则1 用公共方法代表页面所提供的功能
        //po原则3 通常不在PO内添加断言
        sendkeys(By.id("memberSearchInput"),departmentname);

        String text = driver.findElement(parterInfo).getText();
//        System.out.println(text);
        click(By.cssSelector(".ww_icon_AddMember"));
        return this;
    }

    public String getPartyInfo(){
        String text = driver.findElement(parterInfo).getText();
//        System.out.println(text);
        click(By.cssSelector(".js_party_info"));
        return text;
    }

    public String getPartyName(){
        String name = driver.findElement(party_name).getText();
        System.out.println(name);
        return name;
    }

    public ContactPage addDepartMent(String departmentName) throws InterruptedException {

        click(By.linkText("添加"));
        click(By.linkText("添加部门"));
        sendkeys(By.name("name"),departmentName);
        click(By.linkText("选择所属部门"));
        Thread.sleep(3000);
        //第一个部门名称：炎黄春秋
        click(By.xpath("(//ul[@role='group'])[3]/li"));
        click(By.linkText("确定"));
        return this;
    }

    public ContactPage updateDepartment(String departmentname){
        searchDepartment(departmentname);
        click(By.linkText("修改名称"));
        sendkeys(By.name("name"),departmentname+"_testmodify");
        click(By.linkText("保存"));
        return this;
    }

    public void clearAllDepartment() throws InterruptedException {
        //清理部门和成员
        Thread.sleep(5000);
        click((By.linkText("炎黄春秋")));
        //全选部门成员
        click(By.xpath("(//input[@type='checkbox'])[1]"));
        click(By.linkText("删除"));
        click(By.linkText("确认")); //清理完毕成员
        click(By.linkText("测试部"));
        Thread.sleep(2000);
        click(By.linkText("产品销售部"));
        Thread.sleep(2000);
        click(By.xpath("//li[@role='treeitem'][3]//span"));
        click(By.linkText("删除"));
        click(By.linkText("确定")); //清理完毕部门

        //todo: 所有的部门
    }
}
