package com.chen.star.base.exception;

import com.chen.star.base.dto.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

//    /**
//     * 处理自定义的业务异常
//     * @param req
//     * @param e
//     * @return
//     */
//    @ExceptionHandler(value = BizException.class)
//    @ResponseBody
//    public  ResultBody bizExceptionHandler(HttpServletRequest req, BizException e){
//        logger.error("发生业务异常！原因是：{}",e.getErrorMsg());
//        return ResultBody.error(e.getErrorCode(),e.getErrorMsg());
//    }
//

    /**
     * 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public BaseResponse exceptionHandler(Exception e) {
        logger.error("发生异常！原因是:", e);
        return new BaseResponse(500, e.getMessage(), null);
    }


    /**
     * 处理自定义的业务异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public BaseResponse baseExceptionHandler(BaseException e) {
        logger.error("发生业务异常！原因是：{}", e.getMessage());
        return new BaseResponse(e.getCode(), e.getMessage(), null);
    }
}
