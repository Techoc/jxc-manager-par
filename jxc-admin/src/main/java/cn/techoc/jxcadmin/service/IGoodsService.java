package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.pojo.Goods;
import cn.techoc.jxcadmin.query.GoodsQuery;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.Map;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author techoc
 * @since 2021-05-31
 */
public interface IGoodsService extends IService<Goods> {

    Map<String, Object> goodsList(GoodsQuery goodsQuery);

    String genGoodsCode();

    void saveGoods(Goods goods);

    void updateGoods(Goods goods);

    void deleteGoods(Integer id);
}
