package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.query.UserQuery;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

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

    /**
     * 展示用户列表
     *
     * @param userQuery
     * @return
     */
    Map<String, Object> userList(UserQuery userQuery);

    /**
     * 添加用户信息
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 修改用户信息
     *
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户信息
     *
     * @param ids
     */
    void deleteUser(Integer[] ids);
}
