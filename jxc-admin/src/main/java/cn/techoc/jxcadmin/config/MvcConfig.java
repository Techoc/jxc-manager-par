package cn.techoc.jxcadmin.config;

import cn.techoc.jxcadmin.interceptors.NoLoginInterceptors;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * MVC配置类
 *
 * @author techoc
 * @Date 2021/5/26
 */
@Controller
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public NoLoginInterceptors noLoginInterceptors() {
        return new NoLoginInterceptors();
    }

    @Resource
    NoLoginInterceptors noLoginInterceptors;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noLoginInterceptors)
            .addPathPatterns("/**")
            .excludePathPatterns("/index", "user/login", "/css/**", "/error/**", "/images/**",
                "/js/**", "/lib/**");
    }
}
