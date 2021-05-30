package cn.techoc.jxcadmin.mapper;

import cn.techoc.jxcadmin.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author techoc
 * @since 2021-05-28
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Map<String, Object>> queryAllRoles(Integer userId);
}
