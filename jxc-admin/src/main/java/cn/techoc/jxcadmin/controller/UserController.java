package cn.techoc.jxcadmin.controller;


import cn.techoc.jxcadmin.exceptions.ParamsException;
import cn.techoc.jxcadmin.model.RespBean;
import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.service.IUserService;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author techoc
 * @since 2021-05-24
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    @RequestMapping("login")
    @ResponseBody
    public RespBean login(String username, String password, HttpSession session) {
        try {
            User user = userService.login(username, password);
            session.setAttribute("user", user);
            return RespBean.success("用户登录成功!");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("用户登录失败!");
        }
    }
}
