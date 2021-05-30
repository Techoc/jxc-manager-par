package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.pojo.RoleMenu;
import cn.techoc.jxcadmin.mapper.RoleMenuMapper;
import cn.techoc.jxcadmin.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author techoc
 * @since 2021-05-30
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Override
    public List<Integer> queryRoleHasAllMenusByRoleId(Integer roleId) {
        return this.baseMapper.queryRoleHasAllMenusByRoleId(roleId);
    }

    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleNames) {
        return this.baseMapper.findAuthoritiesByRoleName(roleNames);
    }
}
