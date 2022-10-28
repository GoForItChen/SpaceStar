package com.chen.star.base;

import com.chen.star.base.dto.BaseResponse;
import com.chen.star.base.exception.IStatus;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;

/**
 * <p>File：GenericController.java</p>
 * <p>Title: </p>
 * <p>Description:</p>
 * <p>Copyright: Copyright (c) 2015 2015-4-21 下午1:46:45</p>
 * <p>Company: Blocain</p>
 *
 * @author Playguy
 * @version 1.0
 */
public abstract class GenericController
{
    /**
     * 验证Bean实例对象
     */
    @Autowired(required = false)
    protected Validator  validator;


    /**
     * 接口处理结果反馈
     *
     * @param iStatus 异常代码描述
     * @param object      单结果返回对象
     * @return
     * @author Playguy
     */
    protected BaseResponse getBaseResponse(IStatus iStatus, Object object)
    {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setCode(iStatus.getCode());
        baseResponse.setMsg(iStatus.getMessage());
        baseResponse.setData(object);
        return baseResponse;
    }
}
