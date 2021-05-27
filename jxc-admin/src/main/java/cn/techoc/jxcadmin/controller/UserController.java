package cn.techoc.jxcadmin.controller;


import cn.techoc.jxcadmin.exceptions.ParamsException;
import cn.techoc.jxcadmin.model.RespBean;
import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.service.IUserService;

import java.security.Principal;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
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

    /**
     * 用户信息设置页面
     *
     * @return
     */
    @RequestMapping("setting")
    public String setting(Principal principal, Model model) {
        User user = userService.findUserByUserName(principal.getName());
        model.addAttribute("user", user);
        return "user/setting";
    }

    /**
     * 用户信息更新
     *
     * @param user
     * @return
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public RespBean updateUserInfo(User user) {
        userService.updateUserInfo(user);
        return RespBean.success("用户信息更新成功");
    }

    /**
     * 用户密码更新页
     *
     * @return
     */
    @RequestMapping("password")
    public String password() {
        return "user/password";
    }

    /**
     * 用户密码更新
     *
     * @param principal
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     * @return
     */
    @RequestMapping("updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(Principal principal, String oldPassword, String newPassword,
                                       String confirmPassword) {
        userService
                .updateUserPassword(principal.getName(), oldPassword, newPassword, confirmPassword);
        return RespBean.success("用户密码更新成功");
    }

}
