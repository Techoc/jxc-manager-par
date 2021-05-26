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
     * 根据用户名查询用户记录
     *
     * @param username
     * @return
     */
    User findUserByUserName(String username);

    /**
     * 更新用户信息
     *
     * @param user
     */
    void updateUserInfo(User user);

    /**
     * 根据用户名更新密码
     *
     * @param userName
     * @param oldPassword
     * @param newPassword
     * @param confirmPassword
     */
    void updateUserPassword(String userName, String oldPassword, String newPassword,
                            String confirmPassword);
}
