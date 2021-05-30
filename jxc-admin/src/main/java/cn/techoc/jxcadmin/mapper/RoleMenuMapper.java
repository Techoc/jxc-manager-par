package cn.techoc.jxcadmin.mapper;

import cn.techoc.jxcadmin.pojo.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author techoc
 * @since 2021-05-30
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    List<Integer> queryRoleHasAllMenusByRoleId(Integer roleId);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
