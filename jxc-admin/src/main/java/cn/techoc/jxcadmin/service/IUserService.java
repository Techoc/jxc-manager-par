package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author techoc
 * @since 2021-05-24
 */
public interface IUserService extends IService<User> {

    /**
     * 用户登录方法
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     * 根据用户名查询用户记录
     *
     * @param username
     * @return
     */
    User findUserByUserName(String username);
}
