package cn.techoc.jxcadmin;

import cn.techoc.jxcadmin.exceptions.ParamsException;
import cn.techoc.jxcadmin.model.RespBean;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 全局异常处理类
 *
 * @author techoc
 * @Date 2021/5/26
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ParamsException.class)
    @ResponseBody
    public RespBean paramsExceptionHandler(ParamsException e) {
        return RespBean.error(e.getMsg());
    }

    /**
     * 处理非参数异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespBean ExceptionHandler(Exception e) {
        return RespBean.error(e.getMessage());
    }
}
