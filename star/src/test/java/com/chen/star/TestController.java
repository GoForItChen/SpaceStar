package com.chen.star;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
public class TestController {

    @Value("${test}")
    private String test;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test() throws Exception {
        System.out.println(test);
    }

}
