package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.pojo.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author techoc
 * @since 2021-05-30
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    List<Integer> queryRoleHasAllMenusByRoleId(Integer roleId);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
