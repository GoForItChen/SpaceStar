package com.chen.star;

import com.chen.star.entity.Account;
import com.chen.star.service.AccountService;
import com.chen.star.service.DubbotestService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RequestMapping("/test")
@RestController
@RequiredArgsConstructor
public class TestController {

    @Autowired
    private DubbotestService dubbotestService;

    @Autowired
    private AccountService accountService;

    @PersistenceContext
    private EntityManager entityManager;

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    @Transactional
    public String test() {
//        String test = dubbotestService.test();
        Account account = accountService.findByAccountname("chenwei");
        account.setPhone("23");
//        account.setEmail("234");
        return account.getAccountname();
    }

}
