package test;

import DaoApi.Demo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.DriverManager;

/**
 * Created by dlf on 2016/5/12.
 */
public class Test {
    public static void main(String[] args) throws Exception {


        DriverManager.getConnection("jdbc:oracle:thin:@14k5392a14.imwork.net/HIUP","ats_empi","ats_empi");

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "DubboConfig/dubbo-consumer.xml");
        context.start();

        Demo demoService = (Demo) context.getBean("demoService"); //
        String a=demoService.say("aaaa");
        System.out.println(a);
    }
}