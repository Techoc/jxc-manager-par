package cn.techoc.jxcadmin.query;

import lombok.Data;

/**
 * 基础分页
 *
 * @author techoc
 * @since 2021/5/27
 */
@Data
public class BaseQuery {
    private Integer page = 1;
    private Integer limit = 10;
}
