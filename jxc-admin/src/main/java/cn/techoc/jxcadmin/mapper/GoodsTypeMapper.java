package cn.techoc.jxcadmin.mapper;

import cn.techoc.jxcadmin.dto.TreeDto;
import cn.techoc.jxcadmin.pojo.GoodsType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;

/**
 * <p>
 * 商品类别表 Mapper 接口
 * </p>
 *
 * @author techoc
 * @since 2021-05-31
 */
public interface GoodsTypeMapper extends BaseMapper<GoodsType> {

    List<TreeDto> queryAllGoodsTypes();
}
