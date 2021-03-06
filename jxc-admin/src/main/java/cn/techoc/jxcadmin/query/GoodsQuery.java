package cn.techoc.jxcadmin.query;

import java.util.List;
import lombok.Data;

/**
 * @author techoc
 * @since 2021/5/31
 */
@Data
public class GoodsQuery extends BaseQuery{
    private String goodsName;
    private Integer typeId;

    private List<Integer> typeIds;

    // 查询类型 区分库存量是否大于0查询
    /**
     * 1 库存量=0
     * 2 库存量>0
     */
    private Integer type;
}
