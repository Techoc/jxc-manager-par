package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.pojo.User;
import cn.techoc.jxcadmin.mapper.UserMapper;
import cn.techoc.jxcadmin.query.UserQuery;
import cn.techoc.jxcadmin.service.IUserService;
import cn.techoc.jxcadmin.utils.AssertUtil;
import cn.techoc.jxcadmin.utils.StringUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public User findUserByUserName(String username) {
        return this.baseMapper
            .selectOne(new QueryWrapper<User>().eq("is_del", 0).eq("user_name", username));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserInfo(User user) {
        AssertUtil.isTrue(StringUtil.isEmpty(user.getUsername()), "用户名不能为空!");
        User temp = this.findUserByUserName(user.getUsername());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())), "用户名已存在");
        AssertUtil.isTrue(!(this.updateById(user)), "用户信息更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUserPassword(String userName, String oldPassword, String newPassword,
                                   String confirmPassword) {
        User user = this.findUserByUserName(userName);
        AssertUtil.isTrue(null == user, "用户不存在或未登录!");
        AssertUtil.isTrue(StringUtil.isEmpty(oldPassword), "请输入原始密码");
        AssertUtil.isTrue(StringUtil.isEmpty(newPassword), "请输入新密码");
        AssertUtil.isTrue(StringUtil.isEmpty(confirmPassword), "请输入确认密码");
        assert user != null;
        AssertUtil.isTrue(!passwordEncoder.matches(oldPassword, user.getPassword()), "原始密码输入错误!");
        AssertUtil.isTrue(!(newPassword.equals(confirmPassword)), "新密码输入不一致!");
        AssertUtil.isTrue((newPassword.equals(oldPassword)), "新密码与原始密码不能一致!");
        user.setPassword(passwordEncoder.encode(newPassword));
        AssertUtil.isTrue(!(this.updateById(user)), "用户密码更新失败!");
    }

    @Override
    public Map<String, Object> userList(UserQuery userQuery) {
        IPage<User> page = new Page<>(userQuery.getPage(), userQuery.getLimit());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(userQuery.getUsername())) {
            queryWrapper.like("user_name", userQuery.getUsername());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("data", page.getRecords());
        map.put("count", page.getTotal());
        return map;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()), "用户名不能为空!");
        AssertUtil.isTrue(null != this.findUserByUserName(user.getUsername()), "用户名已存在!");
        user.setPassword(passwordEncoder.encode("123456"));
        user.setIsDel(0);
        //TODO 添加权限信息
        AssertUtil.isTrue(!(this.save(user)), "用户记录添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateUser(User user) {
        AssertUtil.isTrue(StringUtils.isBlank(user.getUsername()), "用户名不能为空!");
        User temp = this.findUserByUserName(user.getUsername());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(user.getId())), "用户名已存在!");
        //TODO 更新权限
        AssertUtil.isTrue(!(this.updateById(user)), "用户记录更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteUser(Integer[] ids) {
        AssertUtil.isTrue(null == ids || ids.length == 0, "请选择待删除的记录id!");
        List<User> users = new ArrayList<>();
        for (Integer id : ids) {
            User temp = this.getById(id);
            temp.setIsDel(1);
            users.add(temp);
        }
        AssertUtil.isTrue(!(this.updateBatchById(users)), "用户记录删除失败!");
    }
}
