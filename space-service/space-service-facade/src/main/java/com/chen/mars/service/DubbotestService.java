package com.chen.mars.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chen.mars.entity.Dubbotest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author chenwei
 * @since 2021-07-28
 */
public interface DubbotestService extends IService<Dubbotest> {

    String test();

}
