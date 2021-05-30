package cn.techoc.jxcadmin.service;

import java.util.List;

/**
 * Rbac鉴权模型服务类
 *
 * @author techoc
 * @since 2021/5/30
 */
public interface IRbacService {
    /**
     * 根据登录用户名查询分配的角色
     *
     * @param userName
     * @return
     */
    List<String> findRolesByUserName(String userName);

    List<String> findAuthoritiesByRoleName(List<String> roleNames);
}
