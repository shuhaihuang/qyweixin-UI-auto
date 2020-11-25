package com.work4weixin.web;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * @Author sean
 * @Date 2020/11/15 11:58
 * @Version 1.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ContactPOTest {

    private static MainPage main;

    @BeforeAll
    static void beforeAll() throws IOException, InterruptedException {
        main = new MainPage();
        //清理数据
        main.contact().clearAllDepartment();
        //为什么不放到afterall,如果用例中途停止，teardown不能保证一定会被执行，下次用例可能会因为数据没清理导致失败
    }
    static Stream<Arguments>stringProvider(){
        return Stream.of(
                arguments("lapsan","san","12345678911"),
                arguments("lapsi","si","12345678912"),
                arguments("lapwu","wu","12345678913")
        );
    }
    @Order(1)
    @ParameterizedTest
    @MethodSource("stringProvider")
    void testAddMember(String name,String accountid,String phoneNum) throws InterruptedException {
        main.contact().addMember(name,accountid,phoneNum);
    }


    @Test
    void testSearchDepartment() throws IOException, InterruptedException {
/*      错误的实现方式
        mainPage.contact();
        ContactPage contactPage = new ContactPage(driver);*/

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
        String departmentName = "新天下部";
        assertTrue(main.contact().addDepartMent(departmentName).searchDepartment(departmentName).getPartyInfo().contains(departmentName));
    }

    @Order(3)
    @ParameterizedTest
    @ValueSource(strings = "IT运维部")
    void updateDepartment(String departmentname){
        assertTrue(main.contact().updateDepartment(departmentname).getPartyName().contains("testmodify"));
    }

}
