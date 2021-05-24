package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.mapper.UserMapper;
import cn.techoc.jxcadmin.service.IUserService;
import cn.techoc.jxcadmin.utils.AssertUtil;
import cn.techoc.jxcadmin.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
}
