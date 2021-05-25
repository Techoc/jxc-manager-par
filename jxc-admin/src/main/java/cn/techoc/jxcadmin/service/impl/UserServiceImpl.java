package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.mapper.UserMapper;
import cn.techoc.jxcadmin.service.IUserService;
import cn.techoc.jxcadmin.utils.AssertUtil;
import cn.techoc.jxcadmin.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author techoc
 * @since 2021-05-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User login(String username, String password) {
        AssertUtil.isTrue(StringUtil.isEmpty(username), "用户名不能为空！");
        AssertUtil.isTrue(StringUtil.isEmpty(password), "密码不能为空！");
        User user = this.findUserByUserName(username);
        AssertUtil.isTrue(null == user, "该用户记录不存在或已注销！");
        assert user != null;
        //TODO 引入Spring Security安全框架做密码处理
        AssertUtil.isTrue(!(user.getPassword().equals(password)), "密码错误！");
        return user;
    }

    @Override
    public User findUserByUserName(String username) {
        return this.baseMapper
            .selectOne(new QueryWrapper<User>().eq("is_del", 0).eq("user_name", username));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserInfo(User user) {
        AssertUtil.isTrue(StringUtil.isEmpty(user.getUserName()), "用户名不能为空!");
        User temp = this.findUserByUserName(user.getUserName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())), "用户名已存在");
        AssertUtil.isTrue(!(this.updateById(user)), "用户信息更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserPassword(String userName, String oldPassword, String newPassword,
                                   String confirmPassword) {
        User user = null;
        user = this.findUserByUserName(userName);
        AssertUtil.isTrue(null == user, "用户不存在或未登录!");
        AssertUtil.isTrue(StringUtil.isEmpty(oldPassword), "请输入原始密码");
        AssertUtil.isTrue(StringUtil.isEmpty(newPassword), "请输入新密码");
        AssertUtil.isTrue(StringUtil.isEmpty(confirmPassword), "请输入确认密码");
        assert user != null;
        AssertUtil.isTrue(!(user.getPassword().equals(oldPassword)), "原始密码输入错误!");
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "新密码输入不一致!");
        AssertUtil.isTrue((newPassword.equals(oldPassword)), "新密码与原始密码不能一致!");
        //TODO Spring Security加密密码
        user.setPassword(newPassword);
        AssertUtil.isTrue(!(this.updateById(user)), "用户密码更新失败!");
    }
}
