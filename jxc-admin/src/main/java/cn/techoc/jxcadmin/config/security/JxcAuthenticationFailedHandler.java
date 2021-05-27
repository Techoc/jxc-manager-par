package cn.techoc.jxcadmin.config.security;

import cn.techoc.jxcadmin.model.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 处理登陆失败的Handler
 * </p>
 *
 * @author techoc
 * @since 2021/5/26
 */
@Component
public class JxcAuthenticationFailedHandler extends SimpleUrlAuthenticationFailureHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception)
            throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
                RespBean.error("用户名或密码错误")));
    }
}
