package com.chen.mars;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class TestController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void test() {
//        String encode = bCryptPasswordEncoder.encode("chenwei");
//        System.out.println(encode);
//        System.out.println(bCryptPasswordEncoder.matches("chenwei", encode));
    }

}
