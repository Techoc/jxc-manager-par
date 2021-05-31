package cn.techoc.jxcadmin.service;

import cn.techoc.jxcadmin.dto.TreeDto;
import cn.techoc.jxcadmin.pojo.GoodsType;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * <p>
 * 商品类别表 服务类
 * </p>
 *
 * @author techoc
 * @since 2021-05-31
 */
public interface IGoodsTypeService extends IService<GoodsType> {

    List<TreeDto> queryAllGoodsTypes(Integer typeId);

    List<Integer> queryAllSubTypeIdsByTypeId(Integer typeId);

}
