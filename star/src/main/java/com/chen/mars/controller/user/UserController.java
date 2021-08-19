package com.chen.mars.controller.user;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chen.mars.base.GenericController;
import com.chen.mars.base.dto.BaseResponse;
import com.chen.mars.base.exception.Status;
import com.chen.mars.config.bean.MongoDBHelper;
import com.chen.mars.entity.User;
import com.chen.mars.service.DubbotestService;
import com.chen.mars.service.RedisCacheService;
import com.chen.mars.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@RequiredArgsConstructor
@Api("用户控制器")
@Slf4j
public class UserController extends GenericController {

    @Autowired
    private UserService userService;

    @Autowired
    private MongoTemplate mongoTemplate;

//    @Autowired
//    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisCacheService redisCacheService;

    @Autowired
    private MongoDBHelper mongoDBHelper;

    @Autowired
    private DubbotestService dubbotestService;


    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value = "/register", method = {RequestMethod.POST})
    @ApiOperation(value="用户注册", response = BaseResponse.class)
    public BaseResponse register(@RequestBody User user) {
        Boolean isSuccess = userService.register(user);
        return getBaseResponse(Status.SUCCESS, isSuccess);
    }

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    @PreAuthorize("hasAuthority('menu:finance')")
    public BaseResponse<User> getUser(@PathVariable(value = "id") Integer id) {
        logger.info("查看用户信息info");
        logger.debug("查看用户信息debug");
        logger.error("查看用户信息error");
//        Set<String> set = mongoTemplate.getCollectionNames();
        User user = userService.getById(id);
        String jsonStr = JSONObject.toJSONString(user);
        mongoDBHelper.insert(Document.parse(jsonStr), "user");
//        mongoTemplate.getDb().getCollection("user").insertOne(Document.parse(jsonStr));
        Boolean result = redisCacheService.setValue(id.toString(), user.getUsername());
        String username = redisCacheService.getValue(id.toString()).toString();
        System.out.println("username: " + username);
        return getBaseResponse(Status.SUCCESS, username);
    }

    @GetMapping
    public BaseResponse list(Page<User> page, User user) {
        return getBaseResponse(Status.SUCCESS, userService.page(page, user));

    }

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public BaseResponse test() {
        String test = dubbotestService.test();
        return getBaseResponse(Status.SUCCESS, test);
    }

}
