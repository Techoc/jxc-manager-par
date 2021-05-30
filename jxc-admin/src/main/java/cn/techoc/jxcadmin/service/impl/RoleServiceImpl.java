package cn.techoc.jxcadmin.service.impl;

import cn.techoc.jxcadmin.pojo.Role;
import cn.techoc.jxcadmin.mapper.RoleMapper;
import cn.techoc.jxcadmin.pojo.RoleMenu;
import cn.techoc.jxcadmin.query.RoleQuery;
import cn.techoc.jxcadmin.service.IRoleMenuService;
import cn.techoc.jxcadmin.service.IRoleService;
import cn.techoc.jxcadmin.utils.AssertUtil;
import cn.techoc.jxcadmin.utils.PageResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sun.istack.internal.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author techoc
 * @since 2021-05-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {
    @Resource
    private IRoleMenuService roleMenuService;

    @Override
    public Role findRoleByRoleName(String roleName) {
        return this.baseMapper
            .selectOne(new QueryWrapper<Role>().eq("is_del", 0).eq("name", roleName));
    }

    @Override
    public Map<String, Object> roleList(RoleQuery roleQuery) {
        IPage<Role> page = new Page<>(roleQuery.getPage(), roleQuery.getLimit());
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_del", 0);
        if (StringUtils.isNotBlank(roleQuery.getRoleName())) {
            queryWrapper.like("name", roleQuery.getRoleName());
        }
        page = this.baseMapper.selectPage(page, queryWrapper);
        return PageResultUtil.getResult(page.getTotal(), page.getRecords());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void saveRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()), "请输入角色名!");
        AssertUtil.isTrue(null != this.findRoleByRoleName(role.getName()), "角色名已存在!");
        role.setIsDel(0);
        AssertUtil.isTrue(!(this.save(role)), "角色添加失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateRole(Role role) {
        AssertUtil.isTrue(StringUtils.isBlank(role.getName()), "请输入角色名!");
        Role temp = this.findRoleByRoleName(role.getName());
        AssertUtil.isTrue(null != temp && !(temp.getId().equals(role.getId())), "角色名已存在!");
        AssertUtil.isTrue(!(this.updateById(role)), "角色更新失败!");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteRole(Integer id) {
        AssertUtil.isTrue(null == id, "请选择待删除的记录!");
        Role role = this.getById(id);
        AssertUtil.isTrue(null == role, "待删除的角色不存在!");
        assert role != null;
        role.setIsDel(1);
        AssertUtil.isTrue(!(this.updateById(role)), "角色记录删除失败!");
    }

    @Override
    public List<Map<String, Object>> queryAllRoles(Integer userId) {
        return this.baseMapper.queryAllRoles(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void addGrant(Integer roleId, Integer[] mids) {
        /**
         * 1.参数校验
         * roleId非空必须存在
         * 2.授权
         * 2.1第一次授权
         * 直接批量添加即可
         * 2.2第2+次授权
         * 如果存在原始权限此时删除原始权限然后添加新的权限记录
         * 如果不存在直接批量添加即可
         * 合并2.12.2原始权限不管是否存在先执行权限记录查询如果存在直接删除（根据角色id)
         * 执行批量添加（数组非空数量>0)
         */
        Role role = this.getById(roleId);
        AssertUtil.isTrue(null == role, "待授权角色不存在!");
        int count = roleMenuService.count(new QueryWrapper<RoleMenu>().eq("role_id", roleId));
        if (count > 0) {
            AssertUtil.isTrue(
                !(roleMenuService.remove(new QueryWrapper<RoleMenu>().eq("role_id", roleId))),
                "角色授权失败!");
        }
        if (null != mids && mids.length > 0) {
            List<RoleMenu> roleMenus = new ArrayList<>();
            for (Integer mid : mids) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(mid);
                roleMenus.add(roleMenu);
            }
            AssertUtil.isTrue(!(roleMenuService.saveBatch(roleMenus)), "角色授权失败!");
        }
    }
}
