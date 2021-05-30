package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.pojo.UserRole;
import cn.techoc.jxcadmin.mapper.UserRoleMapper;
import cn.techoc.jxcadmin.service.IUserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author techoc
 * @since 2021-05-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole>
    implements IUserRoleService {

    @Override
    public List<String> findRolesByUserName(String userName) {
        return this.baseMapper.findRolesByUserName(userName);
    }
}
