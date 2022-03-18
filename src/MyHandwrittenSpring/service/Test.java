package MyHandwrittenSpring.service;

import MyHandwrittenSpring.spring.MyApplicationContext;

/**
 * @author ZAY
 */
public class Test {
    public static void main(String[] args) {
        //这里要么传入一个xml文件名,要么传入一个配置文件类
        MyApplicationContext myApplicationContext = new MyApplicationContext(AppConfig.class);
        UserService userService = (UserService) myApplicationContext.getBean("userService");
    }
}
