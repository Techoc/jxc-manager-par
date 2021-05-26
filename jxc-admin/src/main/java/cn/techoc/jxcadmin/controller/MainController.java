package cn.techoc.jxcadmin.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author techoc
 * @Date 2021/5/21
 */
@Controller
public class MainController {

    /**
     * 系统登录页
     *
     * @return
     */
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    /**
     * 系统主页面
     *
     * @return
     */
    @RequestMapping("main")
    public String main() {
        return "main";
    }

    /**
     * 系统欢迎页
     *
     * @return
     */
    @RequestMapping("welcome")
    public String welcome() {
        return "welcome";
    }
}
