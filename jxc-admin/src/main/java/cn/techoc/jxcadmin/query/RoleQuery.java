package cn.techoc.jxcadmin.query;

import lombok.Data;

/**
 * 角色分页
 *
 * @author techoc
 * @since 2021/5/27
 */
@Data
public class RoleQuery extends BaseQuery {
    private String roleName;
}
