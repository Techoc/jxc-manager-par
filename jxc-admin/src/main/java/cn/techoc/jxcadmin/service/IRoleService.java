package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.pojo.Role;
import cn.techoc.jxcadmin.query.RoleQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author techoc
 * @since 2021-05-28
 */
public interface IRoleService extends IService<Role> {

    /**
     * 根据角色名查询角色
     *
     * @param roleName
     * @return
     */
    Role findRoleByRoleName(String roleName);

    /**
     * 展示角色列表
     *
     * @param roleQuery
     * @return
     */
    Map<String, Object> roleList(RoleQuery roleQuery);

    /**
     * 添加角色
     *
     * @param role
     */
    void saveRole(Role role);

    /**
     * 更新角色
     *
     * @param role
     */
    void updateRole(Role role);

    /**
     * 删除角色
     *
     * @param id
     */
    void deleteRole(Integer id);

    /**
     * 通过用户id查询用户角色s
     *
     * @param userId
     * @return
     */
    List<Map<String, Object>> queryAllRoles(Integer userId);

    /**
     * 角色授权接口
     * @param roleId
     * @param mids
     */
    void addGrant(Integer roleId, Integer[] mids);
}
