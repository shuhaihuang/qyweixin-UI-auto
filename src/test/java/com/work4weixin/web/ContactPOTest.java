package com.work4weixin.web;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @Author sean
 * @Date 2020/11/15 11:58
 * @Version 1.0
 */
public class ContactPOTest {

    private static MainPage main;

    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        main = new MainPage();
        //清理数据
        main.contact().clearAllDepartment();
        //为什么不放到afterall,如果用例中途停止，teardown不能保证一定会被执行，下次用例可能会因为数据没清理导致失败
    }


    @Test
    void testAddMember() throws IOException, InterruptedException {
        //打开页面，复用session
//        MainPage mainPage = new MainPage();
        //跳转页面，添加成员
        ContactPage contactPage = main.contact();
        contactPage.addMember("青衣","qing","12345678903");
    }

    @Test
    void testSearchDepartment() throws IOException, InterruptedException {
/*      错误的实现方式
        mainPage.contact();
        ContactPage contactPage = new ContactPage(driver);*/

//        MainPage mainPage = new MainPage();
        ContactPage contactPage = main.contact();
        contactPage.searchDepartment("大中华代表部");
        String text = contactPage.getPartyInfo();
        assertTrue(text.contains("当前部门无任何成员"));
    }

    @Test
    void testSearchDepartmentChain() throws IOException, InterruptedException {
        //链式调用
        assertTrue(main.contact().searchDepartment("大中华代表部").getPartyInfo().contains("无任何成员"));
    }

    @Test
    void testAddDepartment() throws InterruptedException {
        String departmentName = "中东地区部";
        assertTrue(main.contact().addDepartMent(departmentName).searchDepartment(departmentName).getPartyInfo().contains(departmentName));
    }

    @Test
    void updateDepartment(){

    }

}
