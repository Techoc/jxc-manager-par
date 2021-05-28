package cn.techoc.jxcadmin.query;

import lombok.Data;

/**
 * 用户分页
 *
 * @author techoc
 * @since 2021/5/27
 */
@Data
public class UserQuery extends BaseQuery {
    private String username;
}
