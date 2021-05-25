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

    /**
     * 用户信息设置页面
     *
     * @return
     */
    @RequestMapping("setting")
    public String setting(HttpSession session) {
        User user = (User) session.getAttribute("user");
        session.setAttribute("user", userService.getById(user.getId()));
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
        try {
            userService.updateUserInfo(user);
            return RespBean.success("用户信息更新成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("用户信息更新失败!");
        }
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

    @RequestMapping("updateUserPassword")
    @ResponseBody
    public RespBean updateUserPassword(HttpSession session, String oldPassword, String newPassword,
                                       String confirmPassword) {
        try {
            User user = (User) session.getAttribute("user");
            userService
                .updateUserPassword(user.getUserName(), oldPassword, newPassword, confirmPassword);
            return RespBean.success("用户密码更新成功");
        } catch (ParamsException e) {
            e.printStackTrace();
            return RespBean.error(e.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
            return RespBean.error("用户密码更新失败!");
        }
    }
}
