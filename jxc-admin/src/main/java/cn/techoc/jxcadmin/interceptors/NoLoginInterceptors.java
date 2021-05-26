package cn.techoc.jxcadmin.interceptors;


import cn.techoc.jxcadmin.pojo.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录拦截器
 *
 * @author techoc
 * @Date 2021/5/26
 */

public class NoLoginInterceptors implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        if (null == user) {
            //用户未登录
            response.sendRedirect("index");
            return false;
        }
        return true;
    }
}
