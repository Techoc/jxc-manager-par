package cn.techoc.jxcadmin.config.security;

import cn.techoc.jxcadmin.config.ClassPathTldsLoader;
import cn.techoc.jxcadmin.filters.KaptchaCodeFilter;
import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.service.IRbacService;
import cn.techoc.jxcadmin.service.IUserService;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * Spring Security配置类
 *
 * @author techoc
 * @since 2021/5/26
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
    private KaptchaCodeFilter kaptchaCodeFilter;

    @Resource
    private DataSource dataSource;

    @Resource
    private IUserService userService;

    @Resource
    private IRbacService rbacService;

    /**
     * 放行静态资源
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
            "/css/**",
            "/error/**",
            "/images/**",
            "/js/**",
            "/lib/**");
    }

    /**
     * Spring Security配置设置
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //禁用csrf
        http.csrf().disable()
            //进行验证码校验
            .addFilterBefore(kaptchaCodeFilter, UsernamePasswordAuthenticationFilter.class)
            //允许iframe 页面嵌套
            .headers().frameOptions().disable()
            .and()
            .formLogin()
            //用户名参数 密码参数
            .usernameParameter("userName")
            .passwordParameter("password")
            //登陆页面
            .loginPage("/index")
            //登陆处理路由
            .loginProcessingUrl("/login")
            .successHandler(jxcAuthenticationSuccessHandler)
            .failureHandler(jxcAuthenticationFailedHandler)
            .and()
            //退出系统路由
            .logout().logoutUrl("/signout")
            .deleteCookies("JSESSIONID")
            .logoutSuccessHandler(jxcLogoutSuccessHandler)
            .and()
            //7天免登录
            .rememberMe()
            .rememberMeParameter("rememberMe")
            //存在浏览器端的cookie的名称，如果不设置默认也是remember-me
            .rememberMeCookieName("remember-me-cookie")
            //设置token的有效期，即多长时间可以免除重复登录，单位是秒
            .tokenValiditySeconds(7 * 24 * 60 * 60)
            //自定义
            .tokenRepository(persistentTokenRepository())
            .and()
            .authorizeRequests()
            //匹配放行路由
            .antMatchers("/index", "/login", "/image")
            .permitAll()
            //除放行请求，其余请求一律拦截
            .anyRequest().authenticated();
    }

    /**
     * 配置从数据库中获取token
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() {
        /**
         * 1.查询用户所对应的角色
         * 2.根据用户角色查询拥有的权限记录
         *
         */
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)
                throws UsernameNotFoundException {
                User userDetails = userService.findUserByUserName(username);
                List<String> roleNames = rbacService.findRolesByUserName(username);
                List<String> authorities = rbacService.findAuthoritiesByRoleName(roleNames);
                roleNames =
                    roleNames.stream().map(role -> "ROLE_" + role).collect(Collectors.toList());
                authorities.addAll(roleNames);
                userDetails.setAuthorities(
                    AuthorityUtils
                        .commaSeparatedStringToAuthorityList(String.join(",", authorities)));
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

    /**
     * 加载 ClassPathTldsLoader
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ClassPathTldsLoader.class)
    public ClassPathTldsLoader classPathTldsLoader() {
        return new ClassPathTldsLoader();
    }
}
