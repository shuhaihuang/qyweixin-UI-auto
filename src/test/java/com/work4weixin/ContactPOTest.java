package com.work4weixin;

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


    @Test
    void testAddMember() throws IOException, InterruptedException {
        //打开页面，复用session
        MainPage mainPage = new MainPage();
        //跳转页面，添加成员
        ContactPage contactPage = mainPage.contact();
        contactPage.addMember("青衣","qing","12345678903");
    }

    @Test
    void testSearchDepartment() throws IOException, InterruptedException {
        MainPage mainPage = new MainPage();
        ContactPage contactPage = mainPage.contact();
        contactPage.searchDepartment("大中华代表部");
        String text = contactPage.getPartyInfo();
        assertTrue(text.contains("当前部门无任何成员"));
    }

    @Test
    void testSearchDepartmentChain() throws IOException, InterruptedException {
        //链式调用
        assertTrue(new MainPage().contact().searchDepartment("大中华代表部").getPartyInfo().contains("无任何成员"));
    }
}
