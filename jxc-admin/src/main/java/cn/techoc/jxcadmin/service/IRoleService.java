package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.pojo.Role;
import cn.techoc.jxcadmin.query.RoleQuery;
import com.baomidou.mybatisplus.extension.service.IService;
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
     * 展示角色列表
     *
     * @param roleQuery
     * @return
     */
    Map<String, Object> roleList(RoleQuery roleQuery);
}
