package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.service.IRbacService;
import cn.techoc.jxcadmin.service.IRoleMenuService;
import cn.techoc.jxcadmin.service.IUserRoleService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Rbac模型实现类
 *
 * @author techoc
 * @since 2021/5/30
 */
@Service
public class RbacServiceImpl implements IRbacService {

    @Resource
    private IUserRoleService userRoleService;

    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    public List<String> findRolesByUserName(String userName) {
        return userRoleService.findRolesByUserName(userName);
    }

    @Override
    public List<String> findAuthoritiesByRoleName(List<String> roleNames) {
        return roleMenuService.findAuthoritiesByRoleName(roleNames);
    }
}
