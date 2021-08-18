package com.chen.mars.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.mars.entity.Dubbotest;
import com.chen.mars.mapper.DubbotestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenwei
 * @since 2021-07-28
 */
@Service
public class DubbotestServiceImpl extends ServiceImpl<DubbotestMapper, Dubbotest> implements DubbotestService {

    @Autowired
    private DubbotestMapper dubbotestMapper;

    @Override
    public String test() {
        Dubbotest result = dubbotestMapper.selectBy(15);
        return "test" + result.getId();
    }
}
