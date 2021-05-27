package cn.techoc.jxcadmin.config.security;

import cn.techoc.jxcadmin.model.RespBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>处理登陆成功的Handler</p>
 *
 * @author techoc
 * @since 2021/5/26
 */
@Component
public class JxcAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication)
        throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(
            RespBean.success("登录成功")));
    }
}
