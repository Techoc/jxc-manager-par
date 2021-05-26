package cn.techoc.jxcadmin.config.security;

import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.service.IUserService;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security配置类
 *
 * @author techoc
 * @Date 2021/5/26
 */
@SpringBootConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private JxcAuthenticationSuccessHandler jxcAuthenticationSuccessHandler;

    @Resource
    private JxcAuthenticationFailedHandler jxcAuthenticationFailedHandler;

    @Resource
    private JxcLogoutSuccessHandler jxcLogoutSuccessHandler;

    @Resource
    private IUserService userService;

    /**
     * 放行静态资源
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
            "/css/**",
            "/error/**",
            "/images/**",
            "/js/**",
            "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable()
            //TODO 进行验证码校验
            //.addFilterBefore(, UsernamePasswordAuthenticationFilter.class)
            //允许iframe 页面嵌套
            .headers().frameOptions().disable()
            .and()
            .formLogin()
            .usernameParameter("userName")
            .passwordParameter("password")
            .loginPage("/index")
            .loginProcessingUrl("/login")
            .successHandler(jxcAuthenticationSuccessHandler)
            .failureHandler(jxcAuthenticationFailedHandler)
            .and()
            .logout().logoutUrl("/signout")
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(jxcLogoutSuccessHandler)
            .and()
            .authorizeRequests()
            .antMatchers("/index", "/login", "/image")
            .permitAll()
            .anyRequest().authenticated();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {
                User userDetails = userService.findUserByUserName(username);
                return userDetails;
            }
        };
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(encoder());
    }
}
